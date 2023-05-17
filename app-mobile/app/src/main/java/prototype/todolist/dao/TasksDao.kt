package prototype.todolist.dao

import prototype.todolist.dao.api.TasksApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TasksDao {

    companion object{

//      private var BASE_URL = "https://jsonplaceholder.typicode.com/"
        private var BASE_URL = "http://192.168.100.146:8000/api/tasks/"


        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //Doesn't require the adapter
        }
        val apiService: TasksApiInterface = getRetrofit().create(TasksApiInterface::class.java)
    }

    suspend fun getTasks() = apiService.getTasks()

    suspend fun findById(id : Int ) = apiService.findById(id)


//
//    fun insert(task: Task){
//        task.id = ++task_count
//        list_tasks.add(0,task)
//     }
//
//     fun delete(id: Int){
//        var index = this.findIndexById(id)
//         list_tasks.removeAt(index)
//     }
//
//     fun update(task: Task){
//         var index = this.findIndexById(task.id);
//         list_tasks[index] = task
//     }
//
//    private fun findIndexById(id: Int): Int {
//        val index = list_tasks.withIndex().filter { it.value.id == id }.map{it.index}.first()
//        return index
//    }
//
//
//
//    fun getAllTasks(): MutableList<Task> {
//        return list_tasks
//    }
//
//    fun findById(id: Int) : Task {
//        val task = list_tasks.filter { it.id == id }.first()
//        return task
//    }


}