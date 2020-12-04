package ovh.alexisdelhaie.tpandroid.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ovh.alexisdelhaie.tpandroid.activities.MainActivity

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class AppFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val TAG = "ovh.alexisdelhaie.tpandroid.services.AppFirebaseMessagingService"
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        if (p0.data.isNotEmpty()) {
            Log.d(TAG, String.format("Payload: %s", p0.data))
        }

        Log.d(TAG, String.format("Notification: %s", p0.notification?.body))
        p0.notification?.let { sendNotification(it) }
    }

    private fun sendNotification(pN: RemoteMessage.Notification) {
        val i = Intent(this, MainActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val cId = "generalNotificationChannel"
        val notificationBuilder = NotificationCompat.Builder(this, cId)
            .setSmallIcon(android.R.drawable.ic_menu_gallery)
            .setContentTitle(pN.title)
            .setContentText(pN.body)
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT))
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(cId, "General", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        with(NotificationManagerCompat.from(this)) {
            notify(0, notificationBuilder.build())
        }
    }
}