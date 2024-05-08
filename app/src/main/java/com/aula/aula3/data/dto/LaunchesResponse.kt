package com.aula.aula3.data.dto

import com.google.gson.annotations.SerializedName

data class LaunchesResponse(
    @SerializedName("mission_name")
    val missionName: String?,
    @SerializedName("launch_date_utc")
    val launchDateUTC: String?,
    @SerializedName("rocket")
    val rocketResponse: RocketResponse,
    @SerializedName("links")
    val linksResponse: LinksResponse,
    @SerializedName("launch_success")
    val launchSuccess: Boolean?
)

data class RocketResponse(
    @SerializedName("rocket_name")
    val rocketName: String?,
    @SerializedName("rocket_type")
    val rocketType: String?
)

data class LinksResponse(
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String?,
    @SerializedName("wikipedia")
    val wikipedia: String?,
    @SerializedName("video_link")
    val videoLink: String?
)
