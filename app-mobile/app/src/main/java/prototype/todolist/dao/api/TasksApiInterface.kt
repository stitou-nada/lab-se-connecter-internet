package prototype.todolist.dao.api

import prototype.todolist.models.Task
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksApiInterface {

    @GET("find_all")
    suspend fun getTasks(): List<Task>

    @GET("find_by_id/{id}")
    suspend fun findById(@Path("id") id : Int) : Task
}
