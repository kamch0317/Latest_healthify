package com.example.healthify

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthify.databinding.CommentdetailsBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.commentcolumn.*

class CommentDetails : AppCompatActivity() {

    private lateinit var commentdetailsBinding: CommentdetailsBinding
    private lateinit var db : FirebaseFirestore

    private var userNameGet : String? = null
    private var userName : String = " "
    private var commentTitle : String = " "
    private var commentDesc : String = " "
    private var docid : String = " "
    private var foodPlanID : String = " "
    private var currentUser : String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commentdetailsBinding = CommentdetailsBinding.inflate(layoutInflater)
        setContentView(commentdetailsBinding.root)

        supportActionBar?.hide()

        val bundle : Bundle? = intent.extras
        userNameGet = bundle!!.getString("userName")
        userName = userNameGet.toString()
        commentTitle = bundle.getString("commentTitle").toString()
        commentDesc = bundle.getString("commentDesc").toString()
        docid = bundle.getString("commentID").toString()
        foodPlanID = bundle.getString("foodPlanID").toString()
        currentUser = bundle.getString("currentUser").toString()

        commentdetailsBinding.commentTitle.text = commentTitle
        commentdetailsBinding.commentDesc.text = commentDesc
        commentdetailsBinding.commentAuthor.text = userName

        if (currentUser == userName){
            commentdetailsBinding.deletebtn.visibility = View.VISIBLE
        }


        commentdetailsBinding.backbtn.setOnClickListener {
            finish()
        }

        commentdetailsBinding.deletebtn.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Confirm Delete")
            builder.setMessage("Are you sure you want to delete this item?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                deleteCommment(docid, foodPlanID)
                dialog.cancel()
            })
            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            var alert = builder.create()
            alert.show()
        }



    }

    private fun deleteCommment(id : String, foodPlanID : String){

        db = FirebaseFirestore.getInstance()
        db.collection("foodPlan").document(foodPlanID).collection("comment").document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("Firestore Delete", "Success")
                Toast.makeText(this, "Comment Deleted Successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Log.w("Firestore Delete", "Failed")
            }

    }

}