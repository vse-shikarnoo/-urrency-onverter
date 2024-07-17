package psb.test.currencyconverter.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import psb.test.currencyconverter.R
import psb.test.currencyconverter.databinding.FragmentMainLayoutBinding
import psb.test.currencyconverter.model.Currency
import psb.test.currencyconverter.vm.MainViewModel


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

        }
    }

    private fun observe() {
        viewModel.currencyList.observe(viewLifecycleOwner) {
            setSpinners(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            bindError(true)
        }
    }

    private fun bindError(flag: Boolean) {
        TODO("Not yet implemented")
    }

}