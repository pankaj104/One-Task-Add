package abc.com.onetaskadd.activity


import abc.com.onetaskadd.R
import abc.com.onetaskadd.adapter.TodoAdapter
import abc.com.onetaskadd.database.TodoDatabase
import abc.com.onetaskadd.model.TodoModel
import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    val todoList = arrayListOf<TodoModel>()
    private lateinit var recyclerAdapter: TodoAdapter
    private lateinit var bottom_navigation: BottomNavigationView

    lateinit var todoRv: RecyclerView
    val db by lazy {
        TodoDatabase.getDatabase(this)
    }


   
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoRv = findViewById(R.id.todoRv)

        recyclerAdapter = TodoAdapter(todoList)
        bottom_navigation = findViewById(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.graph -> startActivity(Intent(this, GraphActivity:: class.java).setFlags (FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP))
                R.id.profile -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.goal -> startActivity(Intent(this, GoalActivity::class.java))

            }
            true
        }



        todoRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.recyclerAdapter

        }




        db.todoDao().getAllTask().observe(this@MainActivity, Observer {
            if (!it.isNullOrEmpty()) {
                todoList.clear()
                todoList.addAll(it)
                recyclerAdapter.notifyDataSetChanged()
            }

        })









    }


    fun openNewTask(view: View) {
        startActivity(Intent(this, TaskActivity::class.java))
    }


  }



