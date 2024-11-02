package com.example.exampletp2.ui.update

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.exampletp2.R
import com.example.exampletp2.data.model.User
import com.example.exampletp2.databinding.FragmentUpdateBinding
import com.example.exampletp2.ui.UserViewModel


class UpdateFragment : Fragment(), MenuProvider {

    var user: User? = null

    private lateinit var binding: FragmentUpdateBinding
    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = arguments?.getSerializable("user") as User

        binding.etName.setText(user?.let { it.name })
        binding.etLastName.setText(user!!.lastName)
        binding.etAge.setText(user!!.age.toString())


        binding.btnUpdateUser.setOnClickListener {
            val name = binding.etName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString()

            if (name.isNotBlank() && lastName.isNotBlank() && age.isNotBlank()) {

                //val user = User(user.id, name, lastName, age.toInt())
                val userCopy = user!!.copy(name = name, lastName = lastName, age = age.toInt())
                userViewModel.updateUser(user = userCopy)

                findNavController().navigate(R.id.action_updateFragment_to_listFragment)

            } else {
                Toast.makeText(requireContext(), "Complete todos los datos!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btndeleteUser.setOnClickListener {
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

        dialog.setTitle("¿Desea eliminar?")
        dialog.setMessage("Estas seguro que desea eliminar ha ${user!!.name}")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _,_ ->
            userViewModel.deleteUser(user = user!!)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        dialog.create().show()
    }


}