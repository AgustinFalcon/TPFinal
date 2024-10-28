package com.example.exampletp2.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampletp2.R
import com.example.exampletp2.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf("Gato", "Perro", "Loro", "Ballena", "Foca","Gato", "Perro", "Loro", "Ballena", "Foca","Gato", "Perro", "Loro", "Ballena", "Foca","Gato", "Perro", "Loro", "Ballena", "Foca")
        val adapter = ListAdapter(list)

        val layout = LinearLayoutManager(requireContext())
        binding.listadoRecyclerView.layoutManager = layout
        binding.listadoRecyclerView.adapter = adapter


        // Linea divisoria
        val divider = DividerItemDecoration(requireContext(), layout.orientation)
        binding.listadoRecyclerView.addItemDecoration(divider)
    }



}