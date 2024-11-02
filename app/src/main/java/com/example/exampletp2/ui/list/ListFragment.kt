package com.example.exampletp2.ui.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampletp2.R
import com.example.exampletp2.databinding.FragmentListBinding
import com.example.exampletp2.ui.UserViewModel


class ListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentListBinding

    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val list = arrayListOf("Gato", "Perro", "Loro", "Ballena", "Foca","Gato", "Perro", "Loro", "Ballena", "Foca","Gato", "Perro", "Loro", "Ballena", "Foca","Gato", "Perro", "Loro", "Ballena", "Foca")
        val adapter = ListAdapter { user ->
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
        }

        val layout = LinearLayoutManager(requireContext())
        binding.listadoRecyclerView.layoutManager = layout
        binding.listadoRecyclerView.adapter = adapter


        // Linea divisoria
        val divider = DividerItemDecoration(requireContext(), layout.orientation)
        binding.listadoRecyclerView.addItemDecoration(divider)


        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        userViewModel.readAllData.observe(viewLifecycleOwner) { userList ->
            adapter.setList(userList = userList)
        }

        binding.btnDelete.setOnClickListener {
            deleteUser()
        }
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteUser()
                true
            }

            else -> {
                false
            }
        }
    }

    private fun deleteUser() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Desea eliminar a todos?")
        dialog.setMessage("¿Estas seguro que desea eliminar a todos los usuarios?")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _,_ ->
            userViewModel.deleteAll()
        }

        dialog.create().show()
    }


}