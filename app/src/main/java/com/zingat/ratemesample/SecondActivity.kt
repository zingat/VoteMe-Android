package com.zingat.ratemesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.zingat.rateme.Rateme
import com.zingat.rateme.callback.RMEventCallback

class SecondActivity : AppCompatActivity() {
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun dokunBana(view: View) {
        count += 1
        Rateme.getInstance(this)
                .addEvent("button")
                .onRateCallback( object : RMEventCallback {
                    override fun onEvent() {
                    }
                })

        Toast.makeText(this, "$count kere dokunuldu", Toast.LENGTH_SHORT).show()
    }
}