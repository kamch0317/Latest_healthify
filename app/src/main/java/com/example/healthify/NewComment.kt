package com.example.healthify

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthify.databinding.AddnewcommentBinding
import com.example.healthify.databinding.NewfoodplanBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.foodplancomment.*
import java.util.HashMap

class NewComment : AppCompatActivity() {

    private lateinit var db : FirebaseFirestore
    private var userNameGet : String? = null
    private var userName : String = " "
    private var id : String = " "
    private lateinit var binding : AddnewcommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddnewcommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.backbtn.setOnClickListener {
            finish()
        }

        val bundle : Bundle? = intent.extras
        userNameGet = bundle!!.getString("userName")
        userName = userNameGet.toString()
        id = bundle.getString("id").toString()

        binding.addBtn.setOnClickListener {
            addComment()
        }

    }

    private fun addComment(){

        val commentTitle = binding.commentTitle.text.toString()
        val commentDesc = binding.commentDesc.text.toString()

        if (commentTitle.isNullOrEmpty() || commentTitle==" " ||commentDesc.isNullOrEmpty() || commentDesc==" ")
        {
            Toast.makeText(this, "Please make sure all the field is filled!", Toast.LENGTH_LONG)
                .show()
            return
        }

        db = FirebaseFirestore.getInstance()
        val newComment : MutableMap<String, Any> = HashMap()
        newComment["commentTitle"] = commentTitle
        newComment["commentDesc"] = commentDesc
        newComment["username"] = userName

        db.collection("foodPlan").document(id).collection("comment").add(newComment)
            .addOnSuccessListener {
                Toast.makeText(this, "You Added a comment to this Meal Plan Successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Firestore data write error", Toast.LENGTH_LONG).show()
            }

        val intent = Intent(this, FoodPlanComment::class.java)
        intent.putExtra("userName", userName)
        intent.putExtra("id", id)
        startActivity(intent)

    }
}