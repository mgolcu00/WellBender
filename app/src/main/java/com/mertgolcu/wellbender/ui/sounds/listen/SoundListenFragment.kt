package com.mertgolcu.wellbender.ui.sounds.listen

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.core.binding.loadCircleImage
import com.mertgolcu.wellbender.databinding.FragmentListenSoundBinding
import com.mertgolcu.wellbender.domain.model.Sound
import com.mertgolcu.wellbender.ext.formatMillisToMinAndSec
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class SoundListenFragment :
    BaseFragment<FragmentListenSoundBinding, SoundListenViewModel>(R.layout.fragment_listen_sound) {
    override fun getViewModelClass() = SoundListenViewModel::class.java


    var mediaPlayer: MediaPlayer? = null

    var isPlaying = false


    private var onMediaPrepared = MediaPlayer.OnPreparedListener {
        // onSoundPrepared
        binding.imageButtonPlay.setImageResource(R.drawable.ic_play_circle)
        binding.imageButtonPlay.setOnClickListener {
            if (isPlaying)
                stopSound()
            else
                playSound()
        }
    }

    val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())
    var lastTime = 0L
    private var updateTracker = object : Runnable {
        override fun run() {
            if (isPlaying) {
                mediaPlayer?.currentPosition?.toLong()?.let { currentTime ->
                    mediaPlayer?.duration?.let { totalTime ->
                        if (lastTime <= totalTime)
                            if (currentTime > lastTime)
                                lastTime = currentTime
                            else
                                lastTime += 999
                    }
                }
                binding.linearIndicator.progress = lastTime.toInt()
                /*
                Log.d("MEDIA PLAYER", "cp : " + mediaPlayer?.currentPosition.toString())
                Log.d("MEDIA PLAYER", "tt : " + mediaPlayer?.duration.toString())
                */
                // set texts
                binding.textViewCurrentTime.text =
                    lastTime.formatMillisToMinAndSec()
                handler.postDelayed(this, 1000L)
            }
        }
    }


    private var onCompleteListener = MediaPlayer.OnCompletionListener {
        // on sound completed
        stopSound()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.currentSound.observe(viewLifecycleOwner) {
            initUI(it)
        }
    }

    private fun initUI(sound: Sound) {
        binding.apply {

            imageButtonBack.setOnClickListener {
                this@SoundListenFragment.viewModel.popBackStack()
            }

            loadCircleImage(imageViewSound, sound.imageUrl)

            textViewTitle.text = sound.title

            loadSound(sound)
        }
    }

    private fun loadSound(sound: Sound) {
        initMediaPlayer(sound)
        // load sound logic
    }


    private fun initMediaPlayer(sound: Sound) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(sound.soundUrl)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )

        }
        prepareMusic()
        mediaPlayer?.setOnPreparedListener(onMediaPrepared)
        mediaPlayer?.setOnCompletionListener(onCompleteListener)

        // set track
        binding.linearIndicator.max = mediaPlayer?.duration ?: 100
        // set text
        binding.textViewTotalTime.text =
            mediaPlayer?.duration?.toLong()?.formatMillisToMinAndSec()

        binding.textViewCurrentTime.text = lastTime.formatMillisToMinAndSec()


    }

    fun prepareMusic() {
        mediaPlayer?.prepare()
        binding.imageButtonPlay.setImageResource(R.drawable.ic_timer)
    }

    fun playSound() {
        if (mediaPlayer != null) {
            mediaPlayer?.start()
            isPlaying = true
            // set image buttton
            binding.imageButtonPlay.setImageResource(R.drawable.ic_pause_circle)

            updateTracker.run()
        }
    }

    fun stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer?.pause()
            isPlaying = false
            binding.imageButtonPlay.setImageResource(R.drawable.ic_play_circle)


        }
    }

    fun releasePlayer() {
        mediaPlayer?.release()
        isPlaying = false
        mediaPlayer = null
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onResume() {
        super.onResume()
        val sound = viewModel.currentSound.value
        sound?.let {
            initUI(sound)
        }

    }


}
