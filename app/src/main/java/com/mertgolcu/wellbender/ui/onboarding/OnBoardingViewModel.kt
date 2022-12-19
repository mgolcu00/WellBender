package com.mertgolcu.wellbender.ui.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mertgolcu.wellbender.core.base.BaseViewModel
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataStoreManager: WellBenderDataStoreManager
) : BaseViewModel() {
    val currentPage = MutableLiveData(1)
    private val totalPage = 2

    fun nextPage() {
        currentPage.value?.let { cp ->
            val np = cp + 1
            if (np <= totalPage) {
                currentPage.postValue(np)
            }
        }
    }

    fun prevPage() {
        currentPage.value?.let { cp ->
            val np = cp - 1
            if (np > 0) {
                currentPage.postValue(np)
            }
        }
    }

    fun startApp() {
        viewModelScope.launch {
            dataStoreManager.updateHasStartUp(true)
            popBackStack()
        }
    }

}