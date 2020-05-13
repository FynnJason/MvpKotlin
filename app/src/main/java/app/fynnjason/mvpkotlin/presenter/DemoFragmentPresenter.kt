package app.fynnjason.mvpkotlin.presenter

import app.fynnjason.mvpkotlin.base.BasePresenter
import app.fynnjason.mvpkotlin.contract.DemoFragmentContract


class DemoFragmentPresenter : BasePresenter<DemoFragmentContract.DemoView>(),
    DemoFragmentContract.DemoModel {
    override fun getNumber() {
        getView()?.setNumber((0 until 10).random())
    }
}