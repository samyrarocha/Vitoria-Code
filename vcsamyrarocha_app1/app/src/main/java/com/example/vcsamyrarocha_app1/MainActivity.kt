package com.example.vcsamyrarocha_app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count = 0
        val intro: TextView = findViewById<TextView>(R.id.intro)
        intro.text = "Esse é primeiro app"
        println(intro.text)
    }

}