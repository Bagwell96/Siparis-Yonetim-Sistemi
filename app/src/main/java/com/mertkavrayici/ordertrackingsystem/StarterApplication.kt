package com.mertkavrayici.ordertrackingsystem

import android.app.Application
import com.parse.Parse

class StarterApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG)

        Parse.initialize(
            Parse.Configuration.Builder(this)
            .applicationId("Vr3lpANrd6x2KqJ9cG4bUqB31tliSCwZay4Z0NNV")
            .clientKey("qDw8dM6SEJAXU7AjXaNYQBICPaoA7fSDq0c7LINB")
            .server("https://parseapi.back4app.com/")
            .build()
        )
    }
}