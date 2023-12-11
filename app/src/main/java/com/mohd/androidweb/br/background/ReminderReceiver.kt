package com.mohd.androidweb.br.background

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat

class ReminderReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Reminder",Toast.LENGTH_SHORT).show()
        showNotification(context, "Reminder", "It's time for your reminder!")
    }

    private fun showNotification(context: Context?, title: String, description: String) {
        // Create a notification channel (required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default_channel_id","Default Channel",NotificationManager.IMPORTANCE_HIGH)
            val notificationManager = context?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
        // Create the notification
        val notification: Notification = NotificationCompat.Builder(context!!,"default_channel_id")
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .build()

        val notificationManager = context?.getSystemService(NotificationManager::class.java)
        notificationManager?.notify(1,notification)
    }
}