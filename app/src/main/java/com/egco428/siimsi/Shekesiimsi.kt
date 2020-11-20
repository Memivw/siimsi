package com.egco428.siimsi

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.shakesiimsi.*

class Shekesiimsi : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var color = false
    private var view: View? = null
    private var lastUpdate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shakesiimsi)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val siimsi = AnimationUtils.loadAnimation(this, R.anim.siimsi)
        val shakewordAn = AnimationUtils.loadAnimation(this, R.anim.shakeword)
        val siimsishake = AnimationUtils.loadAnimation(this, R.anim.shake)
        wordtv.startAnimation(ttb)
        siimsiIm.startAnimation(siimsi)
        wordshaketv.startAnimation(shakewordAn)
//        val shakesiimsi = findViewById<ImageView>(R.id.siimsiIm)
//        shakesiimsi.setOnClickListener {
//            siimsiIm.startAnimation(siimsishake)
//            val intent = Intent(this,Result::class.java)
//            startActivity(intent)
//        }
    }
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAcceleroMeter(event)
        }
    }
    private fun getAcceleroMeter(event: SensorEvent){
        val value = event.values
        val x = value[0]
        val y = value[1]
        val z = value[2]

        val Accel = (x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()
        if (Accel >= 2){
            if(actualTime - lastUpdate < 200){
                return
            }
//            var n = 0
//            while(n < 5){
//                val siimsishake = AnimationUtils.loadAnimation(this, R.anim.shake)
//                siimsiIm.startAnimation(siimsishake)
//                n++
//            }
//            if(n > 5){
//                val intent = Intent(this,Result::class.java)
//                startActivity(intent)
//            }
            val siimsishake = AnimationUtils.loadAnimation(this, R.anim.shake)
            siimsiIm.startAnimation(siimsishake)
            val rands = (1..50).random()
            Toast.makeText(this, rands.toString(), Toast.LENGTH_SHORT).show()
            lastUpdate = actualTime
        }
//        if(i >= 5){
//            val rands = (1..50).random()
//            val intent = Intent(this,Result::class.java)
//            if(rands != null){
//                Toast.makeText(this, rands.toString(), Toast.LENGTH_SHORT).show()
//                startActivity(intent)
//            }
//        }
    }
}
