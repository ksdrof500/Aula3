package com.aula.aula3.data.repository

import com.aula.aula3.data.ApiService
import com.aula.aula3.domain.DateTransformer
import com.aula.aula3.domain.model.LaunchDomainModel
import com.aula.aula3.domain.repository.GetAllLaunchesRepository

class GetAllLaunchesRepositoryImpl(
    private val apiService: ApiService,
    private val dateTransformer: DateTransformer,
    ) : GetAllLaunchesRepository {

    override suspend fun getAllLaunches(): List<LaunchDomainModel> {
        return LaunchDomainModel.toRepositoryModel(apiService.getAllLaunches(), dateTransformer)
    }
}
