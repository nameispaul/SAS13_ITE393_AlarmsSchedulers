package com.example.sas13_ite393_alarmsschedulers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sas13_ite393_alarmsschedulers.R.*

class MainActivity : AppCompatActivity() {

    private lateinit var alarmMgr: AlarmManager
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val btnSetAlarm = findViewById<Button>(R.id.btnSetAlarm)
        val btnCancelAlarm = findViewById<Button>(R.id.btnCancelAlarm)

        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        btnSetAlarm.setOnClickListener { setAlarm() }
        btnCancelAlarm.setOnClickListener { cancelAlarm() }
    }

    private fun setAlarm() {
        val triggerTime = SystemClock.elapsedRealtime() + 5 * 1000

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, alarmIntent)
        Toast.makeText(this, "Alarm Set in 5 Seconds", Toast.LENGTH_LONG).show()
    }

    private fun cancelAlarm() {
        alarmMgr.let {
            it.cancel(alarmIntent)
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show()
        }
    }
}