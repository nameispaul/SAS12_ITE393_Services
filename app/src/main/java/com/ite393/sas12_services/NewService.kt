package com.ite393.sas12_services
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class NewService : Service() {
    private var player: MediaPlayer? = null
    companion object {
        private var isPlaying = false
        fun isMusicPlaying(): Boolean {
            return isPlaying
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (isPlaying) {
            Toast.makeText(this, "Music is already playing!", Toast.LENGTH_SHORT).show()
            return START_NOT_STICKY
        }

        player = MediaPlayer.create(this, R.raw.background_music)
        player?.let {
            it.isLooping = false
            it.start()
            isPlaying = true

            it.setOnCompletionListener {
                isPlaying = false
                stopSelf()
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        player?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            isPlaying = false
        }
        player = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}