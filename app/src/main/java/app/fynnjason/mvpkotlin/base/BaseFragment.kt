package app.fynnjason.mvpkotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.fynnjason.mvpkotlin.event.EventMessage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 懒加载
 */
abstract class BaseFragment<V, T : BasePresenter<V>> : Fragment() {

    private var mView: View? = null
    private var mIsLoad = false
    private var mIsViewVisible = false
    var mPresenter: T? = null

    abstract fun layoutResID(): Int
    abstract fun createPresenter(): T
    abstract fun initView()
    abstract fun initListener()
    abstract fun initData()
    abstract fun eventBus(code: Int, data: String)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null)
            mView = inflater.inflate(layoutResID(), container, false)
        return mView
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!mIsLoad) {
            mIsLoad = true
            EventBus.getDefault().register(this)
            mPresenter = createPresenter()
            mPresenter?.attachView(this as V)
            initView()
            initListener()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventMessage(eventMessage: EventMessage) {
        eventBus(eventMessage.code, eventMessage.data)
    }
}