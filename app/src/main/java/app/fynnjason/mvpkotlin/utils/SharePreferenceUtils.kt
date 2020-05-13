package app.fynnjason.mvpkotlin.utils

import com.blankj.utilcode.util.SPUtils


object SharePreferenceUtils {
    // 保存token
    fun setToken(token: String) {
        SPUtils.getInstance().put("token", token)
    }

    // 获取token
    fun getToken(): String = SPUtils.getInstance().getString("token")
}