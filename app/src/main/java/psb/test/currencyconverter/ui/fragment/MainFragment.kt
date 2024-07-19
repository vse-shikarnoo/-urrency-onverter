package psb.test.currencyconverter.ui.fragment

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import psb.test.currencyconverter.R
import psb.test.currencyconverter.databinding.FragmentMainLayoutBinding
import psb.test.currencyconverter.model.ConvertResponse
import psb.test.currencyconverter.model.Currency
import psb.test.currencyconverter.ui.adapter.SpinnerAdapter
import psb.test.currencyconverter.vm.MainViewModel
import psb.test.currencyconverter.utils.makeOnItemSelectedListener
import kotlin.math.max
import kotlin.math.min


class MainFragment : Fragment(R.layout.fragment_main_layout) {

    private val binding: FragmentMainLayoutBinding by viewBinding(FragmentMainLayoutBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
        observe()

        viewModel.getCurrencies()
        binding.fromEt.setText(amount.toString())
    }

    private fun setSpinners(currencyList: List<Currency>) {
        binding.fromSpinner.adapter = SpinnerAdapter(requireContext(), currencyList)
        binding.toSpinner.adapter = SpinnerAdapter(requireContext(), currencyList)
    }

    private fun listeners() {
        with(binding) {

            changeIv.setOnClickListener {
                animateChange()
                changeDestinations()
                convert()
            }

            fromSpinner.onItemSelectedListener = makeOnItemSelectedListener {
                setFrom(it.code)
                convert()
            }

            toSpinner.onItemSelectedListener = makeOnItemSelectedListener {
                setTo(it.code)
                convert()
            }

            fromEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    amount = if (p0.isNullOrBlank()) {
                        0.0
                    } else {
                        p0.toString().toDouble()
                    }
                    convert()
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

            loadingLayout.retryBtn.setOnClickListener {
                loadingLayout.progressBar.visibility = View.VISIBLE
                bindError(false)
                convert()
            }
        }
    }

    private fun animateChange() {

        val leftX = min(binding.fromSpinner.x, binding.toSpinner.x)
        val leftY = min(binding.fromSpinner.y, binding.toSpinner.y)
        val rightX = max(binding.toSpinner.x, binding.fromSpinner.x)
        val rightY = max(binding.toSpinner.y, binding.fromSpinner.y)
        val anglesFrom = if (!changeFlag) Pair(-90f, -180f) else Pair(90f, 180f)
        val anglesTo = if (!changeFlag) Pair(90f, 180f) else Pair(-90f, -180f)

        val pathFrom = Path().apply {
            arcTo(
                leftX,
                leftY,
                rightX,
                rightY,
                anglesFrom.first,
                anglesFrom.second,
                true
            )
        }

        val pathTo = Path().apply {
            arcTo(
                leftX,
                leftY,
                rightX,
                rightY,
                anglesTo.first,
                anglesTo.second,
                true
            )
        }
        val animatorFrom =
            ObjectAnimator.ofFloat(binding.fromSpinner, View.X, View.Y, pathFrom).apply {
                duration = 1000
                start()
            }
        val animatorTo =
            ObjectAnimator.ofFloat(binding.toSpinner, View.X, View.Y, pathTo).apply {
                duration = 1000
                start()
            }
        animatorFrom.start()
        animatorTo.start()
    }

    private fun observe() {
        viewModel.currencyList.observe(viewLifecycleOwner) {
            setSpinners(it)
            bindError(false)
            binding.loadingLayout.progressBar.visibility = View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner) {
            binding.loadingLayout.progressBar.visibility = View.GONE
            bindError(true)
        }
        viewModel.convertData.observe(viewLifecycleOwner) {
            bindError(false)
            binding.loadingLayout.progressBar.visibility = View.GONE
            setData(it)
        }
    }

    private fun bindError(flag: Boolean) {
        with(binding) {
            if (flag) {
                loadingLayout.errorTv.visibility = View.VISIBLE
                loadingLayout.retryBtn.visibility = View.VISIBLE
            } else {
                loadingLayout.errorTv.visibility = View.GONE
                loadingLayout.retryBtn.visibility = View.GONE
            }
        }
    }

    private fun convert() {
        viewModel.convert(fromDestination, toDestination, amount)
    }

    private fun setData(convertResponse: ConvertResponse) {
        with(binding) {
            toEt.setText(convertResponse.result.toString())
            rateTv.text = getString(
                R.string.ratio,
                fromDestination,
                String.format("%.2f", convertResponse.info.rate),
                toDestination
            )
        }
    }

    companion object {
        private var fromDestination: String = ""
        private var toDestination: String = ""
        private var amount: Double = 0.0
        private var changeFlag: Boolean = false

        private fun setFrom(from:String){
            if (!changeFlag){
                fromDestination = from
            }else{
                toDestination = from
            }
        }

        private fun setTo(to:String){
            if (!changeFlag){
                toDestination = to
            }else{
                fromDestination = to
            }
        }
        private fun changeDestinations() {
            changeFlag = !changeFlag
            val t = fromDestination
            fromDestination = toDestination
            toDestination = t
        }
    }
}