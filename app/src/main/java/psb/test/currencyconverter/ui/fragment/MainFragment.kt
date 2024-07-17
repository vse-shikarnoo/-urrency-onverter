package psb.test.currencyconverter.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

            fromSpinner.onItemSelectedListener = makeOnItemSelectedListener{
                fromDestination = it
                convert()
            }

            toSpinner.onItemSelectedListener = makeOnItemSelectedListener{
                toDestination = it
                convert()
            }

            fromEt.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    amount = if (p0.isNullOrBlank()){
                        0.0
                    }else{
                        p0.toString().toDouble()
                    }
                    convert()
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
        }
    }

    private fun observe() {
        viewModel.currencyList.observe(viewLifecycleOwner) {
            setSpinners(it)
            bindError(false)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            bindError(true)
        }
        viewModel.convertData.observe(viewLifecycleOwner){
            bindData(it)
            bindError(false)
        }
    }

    private fun bindError(flag: Boolean) {

    }

    private fun convert() {
        viewModel.convert(fromDestination, toDestination, amount)
    }

    private fun bindData(convertResponse: ConvertResponse){
        with(binding){
            toEt.setText(convertResponse.result.toString())

        }
    }


    companion object {
        private var fromDestination: String = ""
        private var toDestination: String = ""
        private var amount: Double = 0.0
    }
}