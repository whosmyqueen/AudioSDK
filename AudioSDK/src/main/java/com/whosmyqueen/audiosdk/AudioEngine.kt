package com.whosmyqueen.audiosdk

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack

object AudioEngine {

    /**
     * A native method that is implemented by the 'audiosdk' native library,
     * which is packaged with this application.
     */
    external fun playSound(path: String)

    private var audioTrack: AudioTrack? = null

    //    这个方法  是C进行调用  通道数
    fun createTrack(sampleRateInHz: Int, nb_channals: Int) {
        val channaleConfig: Int = if (nb_channals == 1) {
            AudioFormat.CHANNEL_OUT_MONO
        } else if (nb_channals == 2) {
            AudioFormat.CHANNEL_OUT_STEREO
        } else {
            AudioFormat.CHANNEL_OUT_MONO
        } //通道数
        val buffersize = AudioTrack.getMinBufferSize(
            sampleRateInHz,
            channaleConfig, AudioFormat.ENCODING_PCM_16BIT
        )
        audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC, sampleRateInHz, channaleConfig,
            AudioFormat.ENCODING_PCM_16BIT, buffersize, AudioTrack.MODE_STREAM
        )
        audioTrack!!.play()
    }

    //C传入音频数据
    fun playTrack(buffer: ByteArray?, lenth: Int) {
        if (audioTrack != null && audioTrack!!.playState == AudioTrack.PLAYSTATE_PLAYING) {
            audioTrack!!.write(buffer!!, 0, lenth)
        }
    }
    init {
        System.loadLibrary("audiosdk")
    }
}