package com.mertgolcu.wellbender.ui.sounds

import androidx.lifecycle.MutableLiveData
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.Sound
import com.mertgolcu.wellbender.domain.model.SoundTag
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 26.12.2022.
 */
@HiltViewModel
class SoundsViewModel @Inject constructor() : BaseViewModel() {

    val soundList = MutableLiveData<List<Sound>>()


    fun fetchSoundList() {
        MOCK_SOUND_LIST.let {
            soundList.postValue(it)
        }
    }


    companion object {
        val MOCK_SOUND_LIST =
            listOf(
                Sound(
                    id = "1",
                    title = "Fire Cracker Sound",
                    imageUrl = "https://picsum.photos/200",
                    duration = 985L,
                    soundUrl = "https://cdn.pixabay.com/download/audio/2022/01/19/audio_0f25c0e66d.mp3?filename=ambience-sounds-8-15136.mp3",
                ),
                Sound(
                    id = "2",
                    title = "Fire Cracker Sound",
                    imageUrl = "https://picsum.photos/200",
                    duration = 600L,
                    soundUrl = "https://cdn.pixabay.com/download/audio/2022/01/19/audio_0f25c0e66d.mp3?filename=ambience-sounds-8-15136.mp3",
                    tags = listOf(
                        SoundTag(
                            id = "1",
                            name = "Chill",
                            color = "orange"
                        ),
                        SoundTag(
                            id = "2",
                            name = "Nature",
                            color = "gray"
                        ),
                        SoundTag(
                            id = "3",
                            name = "Nature",
                        )
                    )
                ),
                Sound(
                    id = "3",
                    title = "Fire Cracker Sound",
                    imageUrl = "https://picsum.photos/200",
                    duration = 120000L,
                    soundUrl = "https://cdn.pixabay.com/download/audio/2022/01/19/audio_0f25c0e66d.mp3?filename=ambience-sounds-8-15136.mp3",
                    tags = listOf(
                        SoundTag(
                            id = "1",
                            name = "Relax",
                            color = "purple_peach"
                        )
                    )
                )
            )
    }
}