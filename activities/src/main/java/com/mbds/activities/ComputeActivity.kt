package com.mbds.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ComputeActivity : AppCompatActivity() {
    private lateinit var field1: EditText
    private lateinit var field2: EditText
    private lateinit var btnCalculer: Button
    private lateinit var resultat: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        btnCalculer = findViewById(R.id.btn_calculer)
        field1 = findViewById(R.id.field_1)
        field2 = findViewById(R.id.field_2)
        resultat = findViewById(R.id.resultat)

        var text1 = field1.text
        var text2 = field2.text

        btnCalculer.isEnabled = !(text1.isNullOrBlank() || text2.isNullOrBlank())

        field1.setOnClickListener {
            btnCalculer.isEnabled = !(text1.isNullOrBlank() && text2.isNullOrBlank())
        }

        field2.setOnClickListener {
            btnCalculer.isEnabled = !(text1.isNullOrBlank() && text2.isNullOrBlank())
        }

        btnCalculer.setOnClickListener {
            if ((text1.isNotBlank() || text1.isNotEmpty()) && (text2.isNotBlank() || text2.isNotEmpty())) {
                resultat.visibility = View.VISIBLE
                resultat.text = (text1.toString().toInt() + text2.toString().toInt()).toString()
            } else {
                resultat.visibility = View.GONE
            }
        }
    }
}