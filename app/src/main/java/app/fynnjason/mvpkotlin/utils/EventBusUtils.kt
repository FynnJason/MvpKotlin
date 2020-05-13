package app.fynnjason.mvpkotlin.utils

import app.fynnjason.mvpkotlin.event.EventMessage
import org.greenrobot.eventbus.EventBus


class EventBusUtils {
    companion object {
        fun post(code: Int, data: String) {
            val msg = EventMessage(code, data)
            EventBus.getDefault().post(msg)
        }

        fun postSticky(code: Int, data: String) {
            val msg = EventMessage(code, data)
            EventBus.getDefault().postSticky(msg)
        }
    }
}