package app.fynnjason.mvpkotlin.contract

/**
 * 示例Contract
 *
 * 包含：登录功能、获取列表数据功能
 */
class DemoContract {
    interface DemoView {
        fun loginSuccess()
        fun loginFailed()
        fun getDataSuccess(list: MutableList<String>?)
        fun getDataFailed()
    }

    interface DemoModel{
        fun login(account:String,password:String)
        fun getData(page:Int)
    }
}