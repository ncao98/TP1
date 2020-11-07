package com.mbds.neighbors.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.mbds.neighbors.NavigationListener
import com.mbds.neighbors.R
import com.mbds.neighbors.data.NeighborRepository
import com.mbds.neighbors.models.Neighbor

class AddNeighborFragment : Fragment(), TextWatcher {
    private lateinit var btnSave: Button

    private lateinit var nameTextEdit: EditText
    private lateinit var avatarUrlTextEdit: EditText
    private lateinit var addressTextEdit: EditText
    private lateinit var phoneNumberTextEdit: EditText
    private lateinit var aboutMeTextEdit: EditText
    private lateinit var webSiteTextEdit: EditText
    private lateinit var phoneLyt: TextInputLayout

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

        nameTextEdit = view.findViewById(R.id.name)
        avatarUrlTextEdit = view.findViewById(R.id.image)
        addressTextEdit = view.findViewById(R.id.address)
        phoneNumberTextEdit = view.findViewById(R.id.phone)
        aboutMeTextEdit = view.findViewById(R.id.about)
        webSiteTextEdit = view.findViewById(R.id.website)
        phoneLyt = view.findViewById(R.id.phoneLyt)

        nameTextEdit.addTextChangedListener(this)
        avatarUrlTextEdit.addTextChangedListener(this)
        addressTextEdit.addTextChangedListener(this)
        phoneNumberTextEdit.addTextChangedListener(this)
        aboutMeTextEdit.addTextChangedListener(this)
        webSiteTextEdit.addTextChangedListener(this)

        btnSave.isEnabled = false

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.add_neighbor)
        }

        btnSave.setOnClickListener {
            val neighbor = Neighbor(
                id = NeighborRepository.getInstance().getNeighbours().size + 1,
                name = nameTextEdit.text.toString(),
                avatarUrl = avatarUrlTextEdit.text.toString(),
                address = addressTextEdit.text.toString(),
                phoneNumber = phoneNumberTextEdit.text.toString(),
                aboutMe = aboutMeTextEdit.text.toString(),
                favorite = false,
                webSite = webSiteTextEdit.text.toString(),
            )

            NeighborRepository.getInstance().createNeighbour(neighbor)

            activity?.onBackPressed()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        btnSave.isEnabled =
            nameTextEdit.text.isNotBlank() && avatarUrlTextEdit.text.isNotEmpty() && addressTextEdit.text.isNotEmpty() && webSiteTextEdit.text.isNotEmpty() && phoneNumberTextEdit.text.isNotEmpty() && aboutMeTextEdit.text.isNotEmpty()
    }

    override fun afterTextChanged(s: Editable?) {
        with(phoneNumberTextEdit.text.toString()) {
            if ((startsWith("06") || startsWith("07")) && length == 10) {
                phoneLyt.error = null
            } else {
                phoneLyt.error = getString(R.string.phone_error)
            }
        }

    }
}