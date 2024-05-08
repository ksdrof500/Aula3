package com.aula.aula3.domain.repository

import com.aula.aula3.domain.model.CompanyInfoDomainModel
import kotlinx.coroutines.flow.Flow

interface GetCompanyInfoRepository {
    suspend fun getCompanyInfo(): CompanyInfoDomainModel
}
