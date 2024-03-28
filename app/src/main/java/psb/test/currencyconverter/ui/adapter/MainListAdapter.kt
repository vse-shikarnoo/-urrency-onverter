package psb.test.currencyconverter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import psb.test.currencyconverter.databinding.ItemMainListLayoutBinding
import psb.test.currencyconverter.model.ValuteInfo

class MainListAdapter(
    private val onItemClick: (position: Int) -> Unit
) :
    ListAdapter<ValuteInfo, MainListAdapter.Holder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<ValuteInfo>() {
        override fun areItemsTheSame(oldItem: ValuteInfo, newItem: ValuteInfo): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ValuteInfo,
            newItem: ValuteInfo
        ): Boolean {
            return oldItem == newItem
        }

    }

    class Holder(
        private val binding: ItemMainListLayoutBinding,
        val onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(valute: ValuteInfo) {
            with(binding) {
                valuteCode.text = valute.charCode
                name.text = valute.name
                val nValue = valute.value
                val pValue = valute.prevValue
                newValue.text = nValue.toString()
                val check = nValue > pValue
                if (check){
                    upImage.visibility = View.VISIBLE
                    downImage.visibility = View.GONE
                }else{
                    upImage.visibility = View.GONE
                    downImage.visibility = View.VISIBLE
                }
                val p = ((nValue-pValue)/pValue*100)
                percent.text=if(check){"+ "}else {""}+String.format("%.2f", p)+"%"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemMainListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}