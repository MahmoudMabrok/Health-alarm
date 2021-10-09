package com.example.healthalarm.workManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.healthalarm.R
import com.example.healthalarm.dataSets.NotificationDataSet.getrandomAvice

class Workmanager(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        startCountingTime()
        return Result.success()
    }

    private fun startCountingTime() {
        try {
            Thread.sleep((5 * 1000).toLong())
            displayNotification(getrandomAvice())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        startCountingTime()
    }

    private fun displayNotification(content: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Channel 1", "Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = NotificationCompat.Builder(applicationContext, "Channel 1")
            .setContentText(content)
            .setSmallIcon(R.drawable.splash)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(uri)
        notificationManager.notify(1, notification.build())
    }

}