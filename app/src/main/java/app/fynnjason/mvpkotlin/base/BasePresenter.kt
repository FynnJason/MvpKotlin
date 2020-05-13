package app.fynnjason.mvpkotlin.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference


abstract class BasePresenter<T> {

    private lateinit var mViewRef: Reference<T>

    fun attachView(view: T) {
        mViewRef = WeakReference<T>(view)
    }

    protected fun getView(): T? {
        return mViewRef.get()
    }

    fun detachView(){
        mViewRef.clear()
    }
}