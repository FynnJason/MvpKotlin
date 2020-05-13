package app.fynnjason.mvpkotlin.net

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level


object HttpHelper {
    private const val logTag = "Http" // 日志输出的tag
    private const val readTime: Long = 10 // 全局读取时间
    private const val writeTime: Long = 10 // 全局写入时间
    private const val connectTime: Long = 10 // 全局连接时间

    // 在Application中初始化
    fun init(application: Application) {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(readTime, TimeUnit.SECONDS)
        builder.writeTimeout(writeTime, TimeUnit.SECONDS)
        builder.connectTimeout(connectTime, TimeUnit.SECONDS)
        // 构建日志拦截器
        val httpLoggingInterceptor = HttpLoggingInterceptor(logTag)
        httpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        httpLoggingInterceptor.setColorLevel(Level.INFO)
        // 添加日志拦截器
        builder.addInterceptor(httpLoggingInterceptor)
        // 构建
        OkGo.getInstance()
            .init(application)
            .setOkHttpClient(builder.build())
            .setCacheMode(CacheMode.NO_CACHE)
            .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
            .retryCount = 3
    }
}