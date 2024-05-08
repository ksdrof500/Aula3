package com.aula.aula3.data.dto

import com.google.gson.annotations.SerializedName

data class CompanyInfoResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("founder")
    val founder: String?,
    @SerializedName("founded")
    val founded: String?,
    @SerializedName("employees")
    val employees: String?,
    @SerializedName("launch_sites")
    val launchSites: Int?,
    @SerializedName("valuation")
    val valuation: Long?
)
