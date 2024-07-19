package psb.test.currencyconverter.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import psb.test.currencyconverter.model.Currency

fun Fragment.toast(stringRes: String) {
    Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun makeOnItemSelectedListener(callback : (Currency) -> Unit): AdapterView.OnItemSelectedListener{
    return object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>,
            view: View,
            position: Int,
            id: Long
        ) {
            callback(parent.getItemAtPosition(position) as Currency)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}
