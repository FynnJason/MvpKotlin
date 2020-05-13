package app.fynnjason.mvpkotlin.net

import app.fynnjason.mvpkotlin.utils.EventBusUtils
import app.fynnjason.mvpkotlin.utils.LogUtils
import app.fynnjason.mvpkotlin.utils.SharePreferenceUtils
import com.blankj.utilcode.util.GsonUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import org.json.JSONException


class HttpUtils private constructor() {
    companion object {
        fun getInstance() = Holder.Instance
    }

    private object Holder {
        val Instance = HttpUtils()
    }

    // postJson示例
    fun postJson(api: String, any: Any, callback: HttpCallback) {
        val headers = HttpHeaders()
        headers.put("token", SharePreferenceUtils.getToken())
        OkGo.post<String>(api)
            .headers(headers)
            .upJson(GsonUtils.toJson(any))
            .execute(MyStringCallback(api, callback))
    }

    // postParams示例
    fun post(api: String, any: Any, callback: HttpCallback) {
        val map = GsonUtils.fromJson(GsonUtils.toJson(any), MutableMap::class.java)
        val params = HttpParams()
        for (key in map.keys) {
            when (map[key]) {
                is Int -> {
                    params.put(key.toString(), map[key].toString().toInt())
                }
                is String -> {
                    params.put(key.toString(), map[key].toString())
                }
                is Long -> {
                    params.put(key.toString(), map[key].toString().toLong())
                }
                is Boolean -> {
                    params.put(key.toString(), map[key].toString().toBoolean())
                }
                is Double -> {
                    params.put(key.toString(), map[key].toString().toDouble())
                }
            }
        }
        val headers = HttpHeaders()
        headers.put("token", SharePreferenceUtils.getToken())
        OkGo.post<String>(api)
            .headers(headers)
            .params(params)
            .execute(MyStringCallback(api, callback))
    }

    // getParams示例
    fun get(api: String, any: Any, callback: HttpCallback) {
        val map = GsonUtils.fromJson(GsonUtils.toJson(any), MutableMap::class.java)
        val params = HttpParams()
        for (key in map.keys) {
            when (map[key]) {
                is Int -> {
                    params.put(key.toString(), map[key].toString().toInt())
                }
                is String -> {
                    params.put(key.toString(), map[key].toString())
                }
                is Long -> {
                    params.put(key.toString(), map[key].toString().toLong())
                }
                is Boolean -> {
                    params.put(key.toString(), map[key].toString().toBoolean())
                }
                is Double -> {
                    params.put(key.toString(), map[key].toString().toDouble())
                }
            }
        }
        val headers = HttpHeaders()
        headers.put("token", SharePreferenceUtils.getToken())
        OkGo.get<String>(api)
            .headers(headers)
            .params(params)
            .execute(MyStringCallback(api, callback))
    }

    /**
     * 自定义处理回调
     */
    private class MyStringCallback(
        private var mApi: String,
        private var mCallback: HttpCallback
    ) : StringCallback() {

        override fun onSuccess(response: Response<String>?) {
            val json = response?.body().toString()
            LogUtils.e("$mApi--->$json")
            try {
                val baseBean = GsonUtils.fromJson(json, BaseBean::class.java)
                when (baseBean.code) {
                    // 成功
                    ApiCode.SUCCESS -> {
                        mCallback.onSuccess("${baseBean.data}")
                    }
                    // 空数据
                    ApiCode.NO_DATA -> {
                        mCallback.onSuccess("${baseBean.data}")
                    }
                    // token失效
                    ApiCode.UN_AUTHORIZED -> {
                        EventBusUtils.post(ApiCode.UN_AUTHORIZED, "")
                    }
                    // 其它
                    else -> {
                        mCallback.onFailure(baseBean.code, "${baseBean.msg}")
                    }
                }
            } catch (e: JSONException) {
                LogUtils.e("$mApi--->${e.message}")
                mCallback.onFailure(ApiCode.JSON_EEROR, "数据异常,请联系客服")
            }
        }

        override fun onError(response: Response<String>?) {
            mCallback.onFailure(ApiCode.SERVER_ERROR, "服务器连接失败,请检查您的网络")
        }

    }
}