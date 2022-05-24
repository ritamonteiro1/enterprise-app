package com.example.di

import com.example.featurehome.navigation.AuthBoundaryImpl
import com.example.navigation.AuthBoundary
import org.koin.dsl.module

val boundaryModule = module {
    factory<AuthBoundary> { AuthBoundaryImpl() }
}