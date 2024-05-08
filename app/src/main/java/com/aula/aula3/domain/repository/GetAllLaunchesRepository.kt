package com.aula.aula3.domain.repository

import com.aula.aula3.domain.model.LaunchDomainModel

interface GetAllLaunchesRepository {
    suspend fun getAllLaunches(): List<LaunchDomainModel>
}
