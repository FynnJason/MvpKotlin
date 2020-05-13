package app.fynnjason.mvpkotlin.net


interface HttpCallback {
    fun onSuccess(data: String)
    fun onFailure(code: Int, msg: String)
}