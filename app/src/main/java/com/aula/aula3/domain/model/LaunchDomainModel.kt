package com.aula.aula3.domain.model

import com.aula.aula3.data.dto.LaunchesResponse
import com.aula.aula3.data.dto.LinksResponse
import com.aula.aula3.data.dto.RocketResponse
import com.aula.aula3.domain.DateTransformer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

data class LaunchDomainModel(
    val missionName: String,
    val launchDate: String,
    val rocket: RocketDomainModel,
    val links: LinksDomainModel,
    val isLaunchSuccess: Boolean,
    val isPastLaunch: Boolean,
    val differenceOfDays: String
) {
    companion object {
        fun toRepositoryModel(
            launchesResponse: List<LaunchesResponse>,
            dateTransformer: DateTransformer
        ): List<LaunchDomainModel> {
            return launchesResponse.map { launchResponse ->

                val launchDate = formatDate(launchResponse.launchDateUTC.orEmpty())

                LaunchDomainModel(
                    missionName = launchResponse.missionName.orEmpty(),
                    launchDate = dateTransformer.dateToDateString(launchDate),
                    rocket = mapRocket(launchResponse.rocketResponse),
                    links = mapLinks(launchResponse.linksResponse),
                    isLaunchSuccess = launchResponse.launchSuccess ?: false,
                    isPastLaunch = dateTransformer.isPast(launchDate),
                    differenceOfDays = dateTransformer.getDifferenceOfDays(launchDate)
                )
            }
        }


        private fun mapLinks(links: LinksResponse) = with(links) {
            LinksDomainModel(
                missionPatchSmall = missionPatchSmall ?: "",
                wikipedia = wikipedia.orEmpty(),
                videoLink = videoLink.orEmpty()
            )
        }

        private fun mapRocket(rocket: RocketResponse) = with(rocket) {
            RocketDomainModel(
                rocketName = rocketName.orEmpty(),
                rocketType = rocketType.orEmpty()
            )
        }

        private fun formatDate(dateValue: String): DateTime {
            val dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

            return dateTimeFormatter.parseDateTime(dateValue.replace("Z", "+0000"))
        }
    }
}

data class RocketDomainModel(
    val rocketName: String,
    val rocketType: String
)

data class LinksDomainModel(
    val missionPatchSmall: String,
    val wikipedia: String,
    val videoLink: String
)
