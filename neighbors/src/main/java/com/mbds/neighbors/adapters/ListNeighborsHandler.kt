package com.mbds.neighbors.adapters

import com.mbds.neighbors.models.Neighbor

interface ListNeighborsHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
}