package com.mohd.androidweb.br.background

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mohd.androidweb.broadcastreceiverallexercises.databinding.ActivityReminderBinding
import java.util.Calendar

class ReminderActivity: AppCompatActivity() {

    lateinit var binding: ActivityReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startReceiverBtn.setOnClickListener {
            setReminder()
        }
    }

    private fun setReminder() {
        // Set the time for the reminder (you can customize this)
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY,2)
            set(Calendar.MINUTE,10)
            set(Calendar.SECOND,0)
        }

        // Create an Intent to be triggered when the alarm fires
        val intent = Intent(this,ReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_MUTABLE)

        // Get the AlarmManager service
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager?

        // Set the alarm to fire at the specified time
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
        Toast.makeText(this, "Reminder set!", Toast.LENGTH_SHORT).show()
    }
}