package com.aula.aula3.data

import com.aula.aula3.data.dto.CompanyInfoResponse
import com.aula.aula3.data.dto.LaunchesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("info")
    suspend fun getCompanyInfo(): CompanyInfoResponse

    @GET("launches")
    suspend fun getAllLaunches(): List<LaunchesResponse>
}
