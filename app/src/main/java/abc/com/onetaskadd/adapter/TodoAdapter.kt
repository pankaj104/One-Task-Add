package abc.com.onetaskadd.adapter

import abc.com.onetaskadd.R
import abc.com.onetaskadd.model.TodoModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter (val todoList: List<TodoModel>) :RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        fun bind(todoModel: TodoModel) {
        with(itemView){

            val txtShowTitle: TextView= findViewById(R.id.txtShowTitle)
            txtShowTitle.text=todoModel.taskName
        }
        }

           }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}