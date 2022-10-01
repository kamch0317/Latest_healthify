package com.example.healthify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthify.databinding.FoodplancommentBinding
import com.google.firebase.firestore.*

class FoodPlanComment : AppCompatActivity() {

    private  lateinit var db : FirebaseFirestore
    private var userNameGet : String? = null
    private var userName : String = " "
    private var id : String = " "
    private var commentList = ArrayList<Comment>()
    private lateinit var foodplancommentBinding: FoodplancommentBinding
    private lateinit var commentrvAdapter: CommentrvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodplancommentBinding  = FoodplancommentBinding.inflate(layoutInflater)
        setContentView(foodplancommentBinding.root)

        supportActionBar?.hide()

        val bundle : Bundle? = intent.extras
        userNameGet = bundle!!.getString("userName")
        userName = userNameGet.toString()
        id = bundle.getString("id").toString()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        foodplancommentBinding.commentrv.layoutManager = layoutManager
        commentrvAdapter = CommentrvAdapter(this, commentList, object : CommentrvAdapter.onFoodItemClickListener {
            override fun onItemClick(position: Int) {
                enterCommentDetails(position)
            }
        })

        foodplancommentBinding.commentrv.adapter = commentrvAdapter

        foodplancommentBinding.backbtn.setOnClickListener{
            finish()
        }

        foodplancommentBinding.addNewComment.setOnClickListener {
            newComment()
        }

    }

    override fun onResume() {
        super.onResume()

        commentList.clear()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        foodplancommentBinding.commentrv.layoutManager = layoutManager
        commentrvAdapter = CommentrvAdapter(this, commentList, object : CommentrvAdapter.onFoodItemClickListener {
            override fun onItemClick(position: Int) {
                enterCommentDetails(position)
            }
        })
        foodplancommentBinding.commentrv.adapter = commentrvAdapter

        loadCommentList()
    }

    private fun newComment(){
        val intent = Intent(this, NewComment::class.java)
        intent.putExtra("userName", userName)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun enterCommentDetails(position : Int){
        val intent = Intent(this, CommentDetails::class.java)
        intent.putExtra("userName", commentList[position].username)
        intent.putExtra("currentUser", userName)
        intent.putExtra("commentTitle", commentList[position].commentTitle)
        intent.putExtra("commentDesc", commentList[position].commentDesc)
        intent.putExtra("commentID", commentList[position].id)
        intent.putExtra("foodPlanID",id)
        startActivity(intent)
    }

    private fun loadCommentList(){

        commentList.clear()
        db = FirebaseFirestore.getInstance()
        db.collection("foodPlan").document(id).collection("comment").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error!=null){
                    Log.e("Firestore error", error.message.toString())
                    return
                }

                for (dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        val comment = dc.document.toObject(Comment::class.java)
                        commentList.add(comment)
                    }
                }
                commentrvAdapter.notifyDataSetChanged()

            }

        })

    }
}
