package app.fynnjason.mvpkotlin.ui.activity

import androidx.fragment.app.Fragment
import app.fynnjason.mvpkotlin.R
import app.fynnjason.mvpkotlin.base.BaseActivity
import app.fynnjason.mvpkotlin.contract.DemoContract
import app.fynnjason.mvpkotlin.presenter.DemoPresenter
import app.fynnjason.mvpkotlin.ui.fragment.DemoFragment
import app.fynnjason.mvpkotlin.utils.toast
import kotlinx.android.synthetic.main.activity_demo.*

/**
 * 示例Activity
 */
class DemoActivity : BaseActivity<DemoContract.DemoView, DemoPresenter>(), DemoContract.DemoView {

    private var mPage = 1

    private val mFragmentManager = supportFragmentManager
    private lateinit var mCurrentFragment: Fragment
    private val m1Fragment = DemoFragment()
    private val m2Fragment = DemoFragment()
    private val m3Fragment = DemoFragment()

    override fun layoutResID(): Int = R.layout.activity_demo

    override fun createPresenter(): DemoPresenter = DemoPresenter()

    override fun initView() {
        m1Fragment.mColor = R.color.colorAccent
        mFragmentManager.beginTransaction().add(R.id.layout_container, m1Fragment).commit()
        mCurrentFragment = m1Fragment

        m2Fragment.mColor = R.color.colorPrimary
        m3Fragment.mColor = R.color.colorPrimaryDark
    }

    override fun initListener() {
        btn_demo_login.setOnClickListener {
            mPresenter?.login(
                et_demo_account.text.toString()
                , et_demo_password.text.toString()
            )
        }
        btn_demo_get_data.setOnClickListener {
            mPresenter?.getData(mPage++)
        }

        tv_1.setOnClickListener {
            menuSwitch(m1Fragment)
        }
        tv_2.setOnClickListener {
            menuSwitch(m2Fragment)
        }
        tv_3.setOnClickListener {
            menuSwitch(m3Fragment)
        }
    }

    override fun initData() {

    }


    override fun eventBus(code: Int, data: String) {

    }

    override fun loginSuccess() {
        toast("登录成功")
    }

    override fun loginFailed() {
        toast("登录失败")
    }

    override fun getDataSuccess(list: MutableList<String>?) {
        toast("列表获取成功")
    }

    override fun getDataFailed() {
        toast("列表获取失败")
    }

    private fun menuSwitch(currentFragment: Fragment) {
        if (currentFragment != mCurrentFragment) {
            if (currentFragment.isAdded) {
                mFragmentManager.beginTransaction()
                    .show(currentFragment)
                    .hide(mCurrentFragment)
                    .commit()
            } else {
                mFragmentManager.beginTransaction()
                    .add(R.id.layout_container, currentFragment)
                    .hide(mCurrentFragment)
                    .commit()
            }
            mCurrentFragment = currentFragment
        }
    }
}
