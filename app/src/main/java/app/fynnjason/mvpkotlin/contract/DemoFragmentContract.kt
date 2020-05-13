package app.fynnjason.mvpkotlin.contract


class DemoFragmentContract {
    interface DemoView {
        fun setNumber(num: Int)
    }

    interface DemoModel {
        fun getNumber()
    }
}