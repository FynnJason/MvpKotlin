package app.fynnjason.mvpkotlin.utils

import android.util.Log
import app.fynnjason.mvpkotlin.BuildConfig


class LogUtils {
    companion object {
        fun e(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.e("MyLog", msg)
            }
        }
    }
}