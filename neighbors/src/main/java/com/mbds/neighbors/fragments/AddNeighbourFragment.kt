package com.mbds.neighbors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mbds.neighbors.R
import com.mbds.neighbors.data.NeighborRepository
import com.mbds.neighbors.data.service.DummyNeighborApiService
import com.mbds.neighbors.models.Neighbor

class AddNeighbourFragment: Fragment() {
    private lateinit var btnSave: Button
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)
        btnSave = view.findViewById(R.id.btnSave)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {
            val neighbor = Neighbor(
                id = NeighborRepository.getInstance().getNeighbours().size + 1,
                name = view.findViewById(R.id.name),
                avatarUrl = view.findViewById(R.id.image),
                address = view.findViewById(R.id.address),
                phoneNumber = view.findViewById(R.id.phone),
                aboutMe = view.findViewById(R.id.about),
                favorite = false,
                webSite = view.findViewById(R.id.website),
            )

            NeighborRepository.getInstance().createNeighbour(neighbor)

            activity?.onBackPressed()
        }
    }
}