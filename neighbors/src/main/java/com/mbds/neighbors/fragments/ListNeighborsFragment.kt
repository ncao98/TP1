package com.mbds.neighbors.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mbds.myapplication.adapters.ListNeighborsAdapter
import com.mbds.neighbors.NavigationListener
import com.mbds.neighbors.R
import com.mbds.neighbors.adapters.ListNeighborsHandler
import com.mbds.neighbors.data.NeighborRepository
import com.mbds.neighbors.data.service.NeighborApiService
import com.mbds.neighbors.models.Neighbor

class ListNeighborsFragment : Fragment(), ListNeighborsHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNeighbor: FloatingActionButton
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        addNeighbor = view.findViewById(R.id.addNeighbor)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter

        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighborFragment())
            }
        }
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        context?.let { context ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmation")
            builder.setMessage("Voulez-vous supprimer ce voisin?")
            builder.setCancelable(false)
            builder.setPositiveButton("Oui") { dialogInterface: DialogInterface?, i: Int ->
                NeighborRepository.getInstance().deleteNeighbour(neighbor)
                recyclerView.adapter?.notifyDataSetChanged()
                dialogInterface?.dismiss()
            }
            builder.setNegativeButton("Non")
            { dialogInterface: DialogInterface?, i: Int ->
                dialogInterface?.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}