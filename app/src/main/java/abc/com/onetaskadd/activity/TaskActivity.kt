package abc.com.onetaskadd.activity

import abc.com.onetaskadd.R
import abc.com.onetaskadd.database.TodoDatabase
import abc.com.onetaskadd.model.TodoModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
const val DB_NAME = "todo.db"


class TaskActivity : AppCompatActivity() {
    lateinit var etTaskName: EditText
    lateinit var saveTask: Button

    val db by lazy {
        TodoDatabase.getDatabase(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        etTaskName = findViewById(R.id.etTaskName)
        saveTask= findViewById(R.id.saveTask)

    }


    fun saveTask(view: View) {
        val taskName = etTaskName.editableText.toString()
              GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                return@withContext db.todoDao ().insertTask(
                        TodoModel(
                                taskName
                        )
                )
            }
            finish()
        }


    }





}