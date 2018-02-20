package com.zingat.ratemesample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.zingat.rateme.Rateme
import com.zingat.rateme.callback.RMEventCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun dokunBana(view: View) {
        val secondActivity = Intent(this, SecondActivity::class.java)
        startActivity(secondActivity)
    }
}
