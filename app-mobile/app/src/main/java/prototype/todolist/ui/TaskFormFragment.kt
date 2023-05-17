package prototype.todolist.ui


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import prototype.todolist.models.Task
import prototype.todolist.repositoryies.TasksRepository
import prototype.todolist.databinding.FragmentTaskFormBinding
import prototype.todolist.utils.Status

class TaskFormFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    companion object {
        val TASKID = "taskid" // Il resemble à une variable static
    }

    private var _binding: FragmentTaskFormBinding? = null
    private val binding get() = _binding!!
    private var taskId =  0 // La valeur 0 signifie que le formulaire est dans l'état d'insertion
    private val tasksRepository = TasksRepository()
    private  var task : Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskId = it.getInt(TASKID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentTaskFormBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Add
        if(taskId == 0){
            this.task = Task(0,"",0, System.currentTimeMillis())
            binding.btnDelete.visibility = View.INVISIBLE
        }
        // Update
        else
        {

            // Call : FindById
            viewModel.findById(taskId).observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        binding.form.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE

                        binding.apply {
                            task = it.data!!
                            editTaskTitle.setText(task?.title)
                            spinner.setSelection(task?.priority!!)
                        }



                    }
                    Status.ERROR -> {
                        binding.form.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Log.d("tasks", it.message.toString())
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.form.visibility = View.GONE
                    }
                }
            })

        }

        binding.apply {

            btnSave.setOnClickListener {
                if(TextUtils.isEmpty(editTaskTitle.text)){
                    Toast.makeText(context, "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val taskTitle = editTaskTitle.text.toString()
                val priority = spinner.selectedItemPosition
                val task = Task(
                    taskId,
                    taskTitle,
                    priority,
                    task?.timestamp!!
                )
//                tasksRepository.save(task)
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                val action = TaskFormFragmentDirections.actionTaskFormFragmentToTaskManagerFragment()
                view.findNavController().navigate(action)

            }
            btnDelete.setOnClickListener {
//                tasksRepository.delete(taskId!!)
                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                val action = TaskFormFragmentDirections.actionTaskFormFragmentToTaskManagerFragment()
                view.findNavController().navigate(action)

            }

        }

    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }


}