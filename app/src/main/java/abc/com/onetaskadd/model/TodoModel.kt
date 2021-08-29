package abc.com.onetaskadd.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class TodoModel(
val taskName: String,
var isFinished: Int =-1,
@PrimaryKey
var id:Long=0

)
