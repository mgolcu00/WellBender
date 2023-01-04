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
                    title = "The Forest",
                    imageUrl = "https://images.pexels.com/photos/338936/pexels-photo-338936.jpeg?cs=srgb&dl=pexels-nejc-ko%C5%A1ir-338936.jpg&fm=jpg&w=1280&h=743&_gl=1*16cajc0*_ga*MjA1MjIxMTc3Ny4xNjY4MTg3ODYw*_ga_8JE65Q40S6*MTY3MjIyNTE0MS45LjEuMTY3MjIyNTE1Mi4wLjAuMA..",
                    duration = 54L,
                    soundUrl = "https://bigsoundbank.com/UPLOAD/mp3/0100.mp3",
                    tags = listOf(
                        SoundTag(
                            id = "1",
                            name = "Birds",
                            color = "orange"
                        ),
                        SoundTag(
                            id = "2",
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