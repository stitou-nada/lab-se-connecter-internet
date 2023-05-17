package prototype.todolist.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import prototype.todolist.R
import prototype.todolist.databinding.FragmentTaskManagerBinding
import prototype.todolist.models.Task
import prototype.todolist.utils.Status


class TaskManagerFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskRecyclerViewAdapter
    private lateinit var binding: FragmentTaskManagerBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // ViewBinding
        binding = FragmentTaskManagerBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Init recyclerView
        adapter =  TaskRecyclerViewAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =  adapter
            floatingActionButton.setOnClickListener{

                val action = TaskManagerFragmentDirections.actionTaskManagerFragmentToTaskFormFragment(taskid = 0 )
                view.findNavController().navigate(action)
            }

        }


        // getUsers observe
        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    retrieveList(it.data!!)
                }
                Status.ERROR -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Log.d("tasks", it.message.toString())
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        })






    }

    private fun retrieveList(tasks: List<Task>) {
        adapter.apply {
            addTasks(tasks)
            notifyDataSetChanged()
        }
    }


    /**
     * Frees the binding object when the Fragment is destroyed.
     */
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }

    // Todo : Implémentez le code du button Ajouter une tâche dans le menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)


    }



}