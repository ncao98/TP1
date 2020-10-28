package com.mbds.activities

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.mbds.activities.ComputeActivity
import com.mbds.activities.R

class MainActivity : AppCompatActivity() {
    private lateinit var clickButton: Button
    private lateinit var tVnbClick: TextView
    private lateinit var computeButton: Button
    private var nbClick = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton =
            findViewById(R.id.btn_click_me) //Pas d'erreur en compilation parce qu'on l'a initialisé avant le onCreate
        tVnbClick = findViewById(R.id.tVnbClick)
        computeButton = findViewById(R.id.btn_compute)

        if (isVisible(nbClick)) tVnbClick.visibility = View.VISIBLE else tVnbClick.visibility =
            View.GONE

        clickButton.setOnClickListener {
            nbClick++
            if (isVisible(nbClick)) tVnbClick.visibility = View.VISIBLE else tVnbClick.visibility =
                View.GONE

            val newText = getString(R.string.click)

            clickButton.text = newText

            tVnbClick.text = "Vous avez cliqué ${nbClick.toString()} fois"
            if (nbClick == 5) {
                clickButton.isEnabled = false
            }
        }

        computeButton.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }

    fun isVisible(nbClick: Int): Boolean {
        if (nbClick == 0) {
            return false
        } else {
            return true
        }
    }
}