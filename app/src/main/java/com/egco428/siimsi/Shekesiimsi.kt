package com.egco428.siimsi

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.shakesiimsi.*

class Shekesiimsi : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var lastUpdate: Long = 0
    private var shake = false
    private var totalshake = 0f
    private val previoustotalshake = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shakesiimsi)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

//        set Animation
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val siimsi = AnimationUtils.loadAnimation(this, R.anim.siimsi)
        val shakewordAn = AnimationUtils.loadAnimation(this, R.anim.shakeword)
        val siimsishake = AnimationUtils.loadAnimation(this, R.anim.shake)
        wordtv.startAnimation(ttb)
        siimsiIm.startAnimation(siimsi)
        wordshaketv.startAnimation(shakewordAn)
    }
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
        shake = true
        val stepsensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepsensor != null)
            sensorManager?.registerListener(this,stepsensor,SensorManager.SENSOR_DELAY_UI)
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

//            set siimsi animation
            val siimsishake = AnimationUtils.loadAnimation(this, R.anim.shakesiimsi)
            siimsiIm.startAnimation(siimsishake)

//          check currentshake to random number and put number to ResultIntent
            if (shake){
                totalshake = event.values[0]
                var currentshake = totalshake.toInt()-previoustotalshake.toInt()
                if (currentshake == 1 || currentshake == -1){
                    val rands = (1..50).random()
                    val fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                    val intent = Intent(this,Result::class.java)
                    onPause()
                    resultsiimsi.startAnimation(fadein)
                    resultsiimsi.visibility
                    numbertv.text = rands.toString()
                    numbertv.startAnimation(fadein)
                    numbertv.visibility
                    val handler = Handler()
                    handler.postDelayed({
                        intent.putExtra("luckynumber",rands.toString())
                        startActivity(intent)
                    }, 1500)
                }
            }
        }
    }
}
