package com.aula.aula3.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aula.aula3.domain.model.CompanyInfoDomainModel
import com.aula.aula3.domain.model.LaunchDomainModel
import com.aula.aula3.domain.repository.GetAllLaunchesRepository
import com.aula.aula3.domain.repository.GetCompanyInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCompanyInfoRepository: GetCompanyInfoRepository,
    private val getAllLaunchesRepository: GetAllLaunchesRepository
) : ViewModel() {

    private val _company: MutableStateFlow<CompanyInfoDomainModel> = MutableStateFlow(CompanyInfoDomainModel())
    val company: StateFlow<CompanyInfoDomainModel> = _company

    private val _launches: MutableStateFlow<List<LaunchDomainModel>> = MutableStateFlow(emptyList())
    val launches: StateFlow<List<LaunchDomainModel>> = _launches

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _launches.value = getAllLaunchesRepository.getAllLaunches()
            _company.value = getCompanyInfoRepository.getCompanyInfo()
        }
    }
}
