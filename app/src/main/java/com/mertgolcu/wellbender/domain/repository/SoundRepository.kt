package com.mertgolcu.wellbender.domain.repository

import com.mertgolcu.wellbender.domain.model.Sound

/**
 * Created by Mert Gölcü on 26.12.2022.
 */

interface ISoundRepository{
    suspend fun getFeaturedSounds():List<Sound>
}
class SoundRepositoryImpl :ISoundRepository{
    override suspend fun getFeaturedSounds(): List<Sound> {
        TODO("Not yet implemented")
    }
}