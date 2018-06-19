package io.ferreyra.speedview

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.ferreyra.speedview.SpeedView.SpeedView
import io.ferreyra.speedview.SpeedView.SpeedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val LOW_RANGE  = 33.3333f  //value expressed as a percentage
        val HIGH_RANGE  = 66.6666f //value expressed as a percentage


        val speedViewModel = SpeedViewModel(0f, LOW_RANGE ,HIGH_RANGE, Color.GREEN, Color.BLUE, Color.RED )
        val speedView = findViewById<SpeedView>(R.id.speedview)

        speedView.updateSpeedView(speedViewModel)
    }
}
