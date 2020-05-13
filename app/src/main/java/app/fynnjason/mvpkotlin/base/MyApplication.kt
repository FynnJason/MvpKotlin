package app.fynnjason.mvpkotlin.base

import android.app.Application
import app.fynnjason.mvpkotlin.net.HttpHelper
import com.blankj.utilcode.util.Utils
import me.jessyan.autosize.AutoSizeConfig


class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
        AutoSizeConfig.getInstance()
            .unitsManager
            .setSupportDP(true)
            .isSupportSP = true
        HttpHelper.init(this)
    }
}