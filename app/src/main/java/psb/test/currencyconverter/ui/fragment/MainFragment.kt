package psb.test.currencyconverter.ui.fragment

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import psb.test.currencyconverter.R
import psb.test.currencyconverter.databinding.FragmentMainLayoutBinding
import psb.test.currencyconverter.model.ConvertResponse
import psb.test.currencyconverter.model.Currency
import psb.test.currencyconverter.vm.MainViewModel
import vk.test.passwordmanager.utils.makeOnItemSelectedListener


class MainFragment : Fragment(R.layout.fragment_main_layout) {

    private val binding: FragmentMainLayoutBinding by viewBinding(FragmentMainLayoutBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
        observe()

        viewModel.getCurrencies()
        binding.fromEt.setText("0.0")
    }

    private fun setSpinners(currencyList: List<Currency>) {
        val currencyCodeList = currencyList.map {
            it.code
        }

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencyCodeList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromSpinner.adapter = adapter
        binding.toSpinner.adapter = adapter


    }

    private fun listeners() {
        with(binding) {

            changeIv.setOnClickListener {
                changeFlag = !changeFlag
                animateChange()
                changeDestinations()
                convert()
            }

            fromSpinner.onItemSelectedListener = makeOnItemSelectedListener {
                fromDestination = it
                convert()
            }

            toSpinner.onItemSelectedListener = makeOnItemSelectedListener {
                toDestination = it
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
                viewModel.getCurrencies()
            }
        }
    }

    private fun animateChange() {

        val leftX = if (changeFlag) binding.fromSpinner.x else binding.toSpinner.x
        val leftY = if (changeFlag) binding.fromSpinner.y else binding.toSpinner.y
        val rightX = if (changeFlag) binding.toSpinner.x else binding.fromSpinner.x
        val rightY = if (changeFlag) binding.toSpinner.y else binding.fromSpinner.y
        val anglesFrom = if (changeFlag) Pair(180f, -180f) else Pair(0f, -180f)
        val anglesTo = if (changeFlag) Pair(0f, -180f) else Pair(180f, -180f)

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
            bindError(true)
        }
        viewModel.convertData.observe(viewLifecycleOwner) {
            Log.d("Test", "observeConvert: $it")
            binding.toEt.setText(it.result.toString())
            bindError(false)
            binding.loadingLayout.progressBar.visibility = View.GONE
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
        viewModel.convert(fromDestination, toDestination, amount, changeFlag)
    }

    companion object {
        private var fromDestination: String = ""
        private var toDestination: String = ""
        private var amount: Double = 0.0
        private var changeFlag: Boolean = false

        private fun changeDestinations(){
            val t = fromDestination
            fromDestination = toDestination
            toDestination = t
        }
    }
}