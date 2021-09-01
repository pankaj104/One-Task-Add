package abc.com.onetaskadd.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TodoModel(
val taskName: String,
var description:String,
var initialValue: String,
var finalValue: String,
var isFinished: Int =-1,
val isChecked: Boolean= false,
@PrimaryKey(autoGenerate = true)
var id:Long=0

)
