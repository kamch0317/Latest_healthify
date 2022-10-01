package com.example.healthify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthify.databinding.HomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.*


class Home : AppCompatActivity() {

    private lateinit var workoutWe: WorkoutMain
    private lateinit var binding: HomeBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var HomeFoodDataadapter: HomeFoodDataadapter
    private var foodList = ArrayList<HomeFoodData>()
    private lateinit var db: FirebaseFirestore
    private var userName = " "
    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val bundle: Bundle? = intent.extras
        val userNameGet = bundle!!.getString("username")

        loadData()

        userName = userNameGet.toString()
        bottomNav = binding.bottomNav
        showinfo()
        workoutWe = WorkoutMain()
        binding.workplan.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = HomeWorkAdapter(workoutList)
        }


        if (this::workoutWe.isInitialized) {
            workoutWe.populateWorkouts()
        } else {

        }
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.foodplanrv.layoutManager = layoutManager
        HomeFoodDataadapter = HomeFoodDataadapter(
            this,
            foodList,
            object : HomeFoodDataadapter.onFoodItemClickListener {
                override fun onItemClick(position: Int) {

                }
            })

        binding.foodplanrv.adapter = HomeFoodDataadapter
        loadSelectedList()


        val navigate = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val menu: Menu = navigate.menu
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homefragment -> {
                    home()

                }

                R.id.tools -> {
                    tools()


                }

                R.id.workout -> {
                    workout()

                }

                R.id.foodplan -> {
                    foodplan()

                }

                R.id.userfragment -> {
                    usersetting()

                }


            }
            true
        }
    }

    override fun onBackPressed() {
        usersetting()
    }

    private fun showinfo() {
        val name = intent.getStringExtra("username")
        if(name == "null"){
            val name1 = binding.userName.setText(username)
        }
        else{
            val name1 = binding.userName.setText(name)
        }
    }

    private fun usersetting() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Usersetting::class.java)
        intent.putExtra("username",name)
        startActivity(intent)
    }

    private fun home() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Home::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }

    private fun tools() {
        storeData()
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, Calculator::class.java)
        intent.putExtra("username", name);
        startActivity(intent)
    }

    private fun workout() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, WorkoutMain::class.java)
        intent.putExtra("username", name);
        workoutList.clear()
        startActivity(intent)
    }

    private fun foodplan() {
        val name = intent.getStringExtra("username")
        val context = binding.bottomNav.context
        val intent = Intent(context, SelectedPage::class.java)
        intent.putExtra("username", name);
        startActivity(intent)

    }

    private fun enterPlanDetails(position : Int){
        val intent = Intent(this,SelectedPlanDetails::class.java)
        intent.putExtra("detailsTitle", foodList[position].setName)
        startActivity(intent)
    }

    private fun loadSelectedList() {

        db = FirebaseFirestore.getInstance()
        db.collection("selected").whereEqualTo("username", userName).addSnapshotListener(object :
            EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("Firestore error", error.message.toString())
                    return
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        val food = dc.document.toObject(HomeFoodData::class.java)
                        foodList.add(food)
                    }
                }
                HomeFoodDataadapter.notifyDataSetChanged()

            }
        })
    }

    private fun storeData(){
        val name = intent.getStringExtra("username").toString()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("USERNAME",name)
        }.apply()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val name = intent.getStringExtra("username").toString()
        if(name == "null"){
            username = sharedPreferences.getString("USERNAME", null).toString()
        }
    }

}