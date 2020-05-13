package app.fynnjason.mvpkotlin.utils

import com.blankj.utilcode.util.LogUtils


class LogUtils {
    companion object {
        fun e(msg: String) {
            LogUtils.e("MyLog：$msg")
        }
    }
}