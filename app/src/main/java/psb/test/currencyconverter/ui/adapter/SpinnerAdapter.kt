package psb.test.currencyconverter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import psb.test.currencyconverter.R
import psb.test.currencyconverter.model.Currency

class SpinnerAdapter(context: Context, currencyList: List<Currency>) :
    ArrayAdapter<Currency>(context, 0, currencyList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_currency_spinner, parent, false)

        val textViewCurrencyCode: TextView = view.findViewById(R.id.currency_code_tv)
        val textViewCurrencySymbol: TextView = view.findViewById(R.id.currency_sumbol_tv)
        val imageViewCurrencyFlag: ImageView = view.findViewById(R.id.currency_flag_iv)

        val currentItem = getItem(position)

        if (currentItem != null) {
            textViewCurrencyCode.text = currentItem.code
            textViewCurrencySymbol.text = currentItem.symbolNative

            val imagePath = context.getString(R.string.flag_image_path, currentItem.code.take(2).lowercase())
            Glide.with(view)
                .load(imagePath)
                .error(R.drawable.baseline_error_24)
                .into(imageViewCurrencyFlag)
        }

        return view
    }
}