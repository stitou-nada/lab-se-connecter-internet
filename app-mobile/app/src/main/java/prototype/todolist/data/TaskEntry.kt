package prototype.todolist.data

import com.squareup.moshi.Json

data class TaskEntry  (
    var id: Int,
    @Json(name = "title") var title: String,
    var priority: Int,
    var timestamp: Long
)

class Tasks : ArrayList<TaskEntry>()

//data class TaskItem(
//    val created_at: String,
//    val id: Int,
//    val priority: Int,
//    val title: String,
//    val updated_at: String
//)

