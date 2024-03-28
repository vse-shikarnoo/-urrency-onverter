package psb.test.currencyconverter.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import psb.test.currencyconverter.R
import psb.test.currencyconverter.databinding.FragmentMainLayoutBinding
import psb.test.currencyconverter.ui.adapter.MainListAdapter
import psb.test.currencyconverter.vm.MainViewModel
import vk.test.passwordmanager.utils.autoCleared

class MainFragment : Fragment(R.layout.fragment_main_layout) {

    private val binding: FragmentMainLayoutBinding by viewBinding(FragmentMainLayoutBinding::bind)
    private val viewModel: MainViewModel by viewModels()
    private var listAdapter: MainListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getValuteInfo()
        initAdapter()
        observe()
    }

    private fun initAdapter() {
        listAdapter = MainListAdapter {}
        with(binding.mainList) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun observe() {
        viewModel.valuteInfo.observe(viewLifecycleOwner) {
            val timestamp = it.timestamp
            val date = timestamp.substringBefore("T")
            val time = timestamp.substringAfter("T").substringBefore("+")
            binding.date.text = date
            binding.time.text = time
            listAdapter.submitList(it.valute.values.toList())
        }
    }

}