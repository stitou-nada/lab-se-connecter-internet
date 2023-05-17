package prototype.todolist.repositoryies

import prototype.todolist.dao.TasksDao
import prototype.todolist.models.Task

class TasksRepository () {

    private val tasksDao = TasksDao()

    suspend fun getTasks() = tasksDao.getTasks()

    suspend fun findById(id : Int) = tasksDao.findById(id)

    suspend fun delete(id : Int) = tasksDao.delete(id)

    suspend fun save(task : Task){
        if(task.id == 0){
            // save
            tasksDao.save(task)
        }else{
            // update
            tasksDao.update(task)

        }

    }

//    private fun insert(task: Task) = taskDao.insert(task)
//    private fun update(task: Task) = taskDao.update(task)
//    fun delete(taskId: Int) = taskDao.delete(taskId)
//    fun getAllTasks() = taskDao.getAllTasks()
//    fun findById(id: Int) = taskDao.findById(id)
//    fun newTask(): Task {
//        return Task(0,"",0,System.currentTimeMillis())
//    }
//
//    fun save(task: Task) {
//
//        if(task.id == 0){
//            this.insert(task)
//        }else{
//            this.update(task)
//        }
//    }
}