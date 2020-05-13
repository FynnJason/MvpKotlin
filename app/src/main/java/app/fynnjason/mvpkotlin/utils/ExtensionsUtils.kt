package app.fynnjason.mvpkotlin.utils

import android.widget.Toast
import app.fynnjason.mvpkotlin.base.MyApplication


fun Any.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.instance, msg, length).show()
}