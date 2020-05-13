package app.fynnjason.mvpkotlin.presenter

import app.fynnjason.mvpkotlin.base.BasePresenter
import app.fynnjason.mvpkotlin.contract.DemoContract

/**
 * 示例Presenter
 */
class DemoPresenter : BasePresenter<DemoContract.DemoView>(), DemoContract.DemoModel {

    override fun login(account: String, password: String) {
        // 模拟网络请求登录
        if (account.isNotEmpty() && password.isNotEmpty()) {
            getView()?.loginSuccess()
        } else {
            getView()?.loginFailed()
        }
    }

    override fun getData(page: Int) {
        // 模拟网络请求列表数据
        if (page < 2) {
            getView()?.getDataSuccess(null)
        } else {
            getView()?.getDataFailed()
        }
    }


}