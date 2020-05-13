package app.fynnjason.mvpkotlin.base

import android.app.Application
import com.blankj.utilcode.util.Utils


class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
    }
}