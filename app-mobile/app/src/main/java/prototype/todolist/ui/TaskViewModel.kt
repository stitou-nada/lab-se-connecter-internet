package prototype.todolist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import prototype.todolist.data.TaskApi
import prototype.todolist.data.TaskEntry
import prototype.todolist.data.TaskRepository

class TaskViewModel : ViewModel()  {

    private val taskRepository = TaskRepository()

    public var list_tasks = MutableLiveData<List<TaskEntry>>()

    init {
        list_tasks.value = mutableListOf<TaskEntry>()
    }

    public fun getTasks() {
        viewModelScope.launch {
            try {
                val listResult = TaskApi.retrofitService.getTasks()
                list_tasks.value = listResult
            } catch (e: Exception) {
                 val msg =   "Failure: ${e.message}"
            }
        }
    }

//    public fun setValue(msg:String){
//        _status.value = msg
//    }
}