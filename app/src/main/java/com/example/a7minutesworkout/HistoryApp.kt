package com.example.a7minutesworkout

import android.app.Application

class HistoryApp : Application() {
    val db by lazy{
        HistoryDatabase.getInstance(this)
    }
}