package app.fynnjason.mvpkotlin.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.fynnjason.mvpkotlin.event.EventMessage
import app.fynnjason.mvpkotlin.utils.LogUtils
import com.gyf.immersionbar.ImmersionBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity() {

    var mPresenter: T? = null
    lateinit var mContext: Context
    lateinit var mActivity: Activity
    lateinit var mImmersionBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.e("当前页面：${javaClass.simpleName}")
        mContext = this
        mActivity = this
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.init()
        EventBus.getDefault().register(this)
        setContentView(layoutResID())
        initView()
        initListener()
        initData()
    }

    abstract fun layoutResID(): Int
    abstract fun createPresenter(): T
    abstract fun initView()
    abstract fun initListener()
    abstract fun initData()
    abstract fun eventBus(code: Int, data: String)

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventMessage(eventMessage: EventMessage) {
        eventBus(eventMessage.code, eventMessage.data)
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        val fragmentTag = "android:support:fragments"
        outState.remove(fragmentTag)
    }
}