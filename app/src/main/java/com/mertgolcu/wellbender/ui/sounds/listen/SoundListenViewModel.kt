package com.mertgolcu.wellbender.ui.sounds.listen

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.model.Sound
import com.mertgolcu.wellbender.ui.read.ReadBlogFragmentArgs
import com.mertgolcu.wellbender.ui.sounds.SoundsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@HiltViewModel
class SoundListenViewModel @Inject constructor() : BaseViewModel() {

    val currentSound = MutableLiveData<Sound>()

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        with(SoundListenFragmentArgs.fromBundle(extras)) {
            getSoundById(this.soundId)
        }
    }

    fun getSoundById(id: String) {
        SoundsViewModel.MOCK_SOUND_LIST.find { s ->
            s.id == id
        }.apply {
            currentSound.postValue(this)
        }

    }
}