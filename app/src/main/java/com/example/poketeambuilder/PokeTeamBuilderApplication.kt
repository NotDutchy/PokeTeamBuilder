package com.example.poketeambuilder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokeTeamBuilderApplication : Application() {

    @Override
    override fun onCreate() {
        super.onCreate()
    }
}