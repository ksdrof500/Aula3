package com.aula.aula3.data.repository

import com.aula.aula3.data.ApiService
import com.aula.aula3.domain.model.CompanyInfoDomainModel
import com.aula.aula3.domain.repository.GetCompanyInfoRepository

class GetCompanyInfoRepositoryImpl(val apiService: ApiService) : GetCompanyInfoRepository {

    override suspend fun getCompanyInfo(): CompanyInfoDomainModel  {
        return CompanyInfoDomainModel.toDomainModel(apiService.getCompanyInfo())
    }
}
