package app.fynnjason.mvpkotlin.ui.fragment

import app.fynnjason.mvpkotlin.R
import app.fynnjason.mvpkotlin.base.BaseFragment
import app.fynnjason.mvpkotlin.contract.DemoFragmentContract
import app.fynnjason.mvpkotlin.presenter.DemoFragmentPresenter
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * 示例Fragment
 */
class DemoFragment : BaseFragment<DemoFragmentContract.DemoView, DemoFragmentPresenter>(),
    DemoFragmentContract.DemoView {

    var mColor: Int = 0

    override fun layoutResID(): Int = R.layout.fragment_demo

    override fun createPresenter(): DemoFragmentPresenter = DemoFragmentPresenter()

    override fun initView() {
        layout_parent.setBackgroundResource(mColor)
    }

    override fun initListener() {
        btn_add.setOnClickListener { }
    }

    override fun initData() {

    }

    override fun eventBus(code: Int, data: String) {

    }

    override fun setNumber(num: Int) {
        tv_number.text = num.toString()
    }
}