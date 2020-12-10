package com.egco428.siimsi

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.feedfish.*
import kotlinx.android.synthetic.main.shakesiimsi.*

class FeedFishActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var lastUpdate: Long = 0
    private var shake = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedfish)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

    }
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
        val stepsensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepsensor != null)
            sensorManager?.registerListener(this,stepsensor,SensorManager.SENSOR_DELAY_UI)

    }
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
    override fun onSensorChanged(event:  SensorEvent?) {
        if(event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAcceleroMeter(event)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("Not yet implemented")
    }
//    get acc to feed fish
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
            val foodAn = AnimationUtils.loadAnimation(this, R.anim.ttb)
            foodimg.startAnimation(foodAn)
            foodimg.visibility
            onPause()
            val intent = Intent(this,WishActivity::class.java)
            val handler = Handler()
                handler.postDelayed({
                    startActivity(intent)
                }, 1500)

        }
    }

}