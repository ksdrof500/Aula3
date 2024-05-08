package com.aula.aula3.di

import com.aula.aula3.data.repository.GetAllLaunchesRepositoryImpl
import com.aula.aula3.data.repository.GetCompanyInfoRepositoryImpl
import com.aula.aula3.domain.DateTransformer
import com.aula.aula3.domain.DateTransformerImpl
import com.aula.aula3.domain.repository.GetAllLaunchesRepository
import com.aula.aula3.domain.repository.GetCompanyInfoRepository
import org.koin.dsl.module

val dataModule = module {
    factory<GetAllLaunchesRepository> {
        GetAllLaunchesRepositoryImpl(get(), get())
    }

    factory<GetCompanyInfoRepository> {
        GetCompanyInfoRepositoryImpl(get())
    }

    single<DateTransformer> {
        DateTransformerImpl()
    }
}