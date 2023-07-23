package com.example.firebasetestapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var submit: Button
    lateinit var nameView: TextView

    // Write a message to the database
    val database = FirebaseDatabase.getInstance()
    val dbRef = database.reference.child("Users")
    val dbRef2 = database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.editTextName)
        submit = findViewById(R.id.buttonSubmit)
        nameView = findViewById(R.id.textViewName)

        dbRef2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val username = snapshot.child("Users").child("userName").value.toString()
                nameView.text = username
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        submit.setOnClickListener {
            val username = name.text.toString()
            dbRef.child("userName").setValue(username)
        }
    }
}