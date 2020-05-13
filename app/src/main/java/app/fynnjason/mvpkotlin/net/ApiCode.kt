package app.fynnjason.mvpkotlin.net


class ApiCode {
    companion object {
        const val SUCCESS = 200 // 成功
        const val NO_DATA = 201 // 数据空
        const val UN_AUTHORIZED = 401 // 授权失败
        val INVALID_AUTH = 403 // 无权访问
        val INVALID_REQUEST = 400 // 参数有误
        const val SERVER_ERROR = 500 // 服务器异常
        const val JSON_EEROR = 600 // 解析异常
    }
}