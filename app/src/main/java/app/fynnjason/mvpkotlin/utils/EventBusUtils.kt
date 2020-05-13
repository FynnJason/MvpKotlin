package app.fynnjason.mvpkotlin.utils

import app.fynnjason.mvpkotlin.event.FJEvent
import org.greenrobot.eventbus.EventBus


class EventBusUtils {
    companion object {
        fun post(code: Int, data: String) {
            val msg = FJEvent(code, data)
            EventBus.getDefault().post(msg)
        }

        fun postSticky(code: Int, data: String) {
            val msg = FJEvent(code, data)
            EventBus.getDefault().postSticky(msg)
        }
    }
}