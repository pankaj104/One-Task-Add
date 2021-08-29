package abc.com.onetaskadd.database

import abc.com.onetaskadd.activity.DB_NAME
import abc.com.onetaskadd.model.TodoModel
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoModel::class],version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object{
        @Volatile
        private  var  INSTANCE: TodoDatabase? =null
        fun getDatabase(context: Context): TodoDatabase {
            val tempInsance = INSTANCE
            if (tempInsance != null) {
                return tempInsance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase:: class.java,
                        DB_NAME
                ).build()
                INSTANCE= instance
                return  instance
            }
        }
    }
}