package com.aula.aula3.domain.model

import com.aula.aula3.data.dto.CompanyInfoResponse

data class CompanyInfoDomainModel(
    val name: String,
    val founder: String,
    val founded: String,
    val employees: String,
    val launchSites: String,
    val valuation: String
) {
    constructor() : this("","","","","","")

    companion object {
        fun toDomainModel(companyInfoRepositoryModel: CompanyInfoResponse) = with(companyInfoRepositoryModel) {
            CompanyInfoDomainModel(
                name = name.orEmpty(),
                founder = founder.orEmpty(),
                founded = founded.orEmpty(),
                employees = employees.orEmpty(),
                launchSites = launchSites.toString(),
                valuation = valuation.toString()
            )
        }
    }
}
