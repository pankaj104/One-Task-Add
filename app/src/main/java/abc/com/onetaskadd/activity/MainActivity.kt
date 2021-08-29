package abc.com.onetaskadd.activity

import abc.com.onetaskadd.R
import abc.com.onetaskadd.adapter.TodoAdapter
import abc.com.onetaskadd.database.TodoDatabase
import abc.com.onetaskadd.model.TodoModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val todoList = arrayListOf<TodoModel>()
    var recyclerAdapter= TodoAdapter(todoList)
    lateinit var layoutmanger: RecyclerView.LayoutManager
    lateinit var todoRv:  RecyclerView
    val db by lazy {
       TodoDatabase.getDatabase(this)
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoRv= findViewById(R.id.todoRv)
        todoRv.apply {
            layoutmanger= LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.recyclerAdapter

        }
//        todoRv.adapter= recyclerAdapter
//        todoRv.layoutManager=LinearLayoutManager(this)

        db.todoDao().getAllTask().observe(this, Observer {
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