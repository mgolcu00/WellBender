package com.mertgolcu.wellbender.domain.store

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.mertgolcu.wellbender.domain.model.UserPreferences
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager.PreferencesKeys.AVATAR_URL
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager.PreferencesKeys.HAS_START_UP
import com.mertgolcu.wellbender.domain.store.WellBenderDataStoreManager.PreferencesKeys.USER_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.util.prefs.Preferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mert Gölcü on 16.12.2022.
 */

private val Context.dataStore by preferencesDataStore(name = "user_preferences_v2")


@Singleton
class WellBenderDataStoreManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val TAG = "DATA_STORE_PREF_MANAGER : "
    private val dataStore = context.dataStore

    val userPreferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading user preferences ", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }

        }.map { preferences ->
            val name = preferences[USER_NAME] ?: DEFAULT_USER_NAME
            val hasStartUp = preferences[HAS_START_UP] ?: DEFAULT_HAS_START_UP
            val avatarUrl = preferences[AVATAR_URL] ?: DEFAULT_AVATAR_URL
            UserPreferences(name, hasStartUp, avatarUrl)
        }

    suspend fun updateUserName(name: String) {
        dataStore.edit {
            it[USER_NAME] = name
        }
    }

    suspend fun updateHasStartUp(hasStartUp: Boolean) {
        dataStore.edit {
            it[HAS_START_UP] = hasStartUp
        }
    }

    suspend fun updateAvatarUrl(avatarUrl: String) {
        dataStore.edit {
            it[AVATAR_URL] = avatarUrl
        }
    }

    private object PreferencesKeys {
        val USER_NAME = stringPreferencesKey("name")
        val HAS_START_UP = booleanPreferencesKey("has_start_up")
        val AVATAR_URL = stringPreferencesKey("avatar_url")
    }

    companion object {
        const val DEFAULT_USER_NAME = "No Name"
        const val DEFAULT_HAS_START_UP = false
        const val DEFAULT_AVATAR_URL = "https://avatars2.githubusercontent.com/u/44591905"
    }
}