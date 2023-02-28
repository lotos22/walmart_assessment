package com.android.walmart_assessment.presentation.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.walmart_assessment.data.Country
import com.android.walmart_assessment.databinding.FragmentCountriesBinding
import com.android.walmart_assessment.utils.Di
import com.android.walmart_assessment.utils.EventObserver

class CountriesFragment : Fragment() {

    private val countriesVm: CountriesVM by viewModels { Di.getCountriesVMFactory() }
    private lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesBinding.inflate(inflater, container, false).apply {
            vm = countriesVm
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        binding.lifecycleOwner = viewLifecycleOwner
        countriesVm.listCountries.observe(viewLifecycleOwner) {
            showContent(it)
        }
        countriesVm.errorMsg.observe(viewLifecycleOwner, EventObserver { msg ->
            showError(msg)
        })
    }

    private fun setupViews() {
        binding.rvCountries.apply {
            adapter = CountriesAdapter()
            val manager = layoutManager as LinearLayoutManager
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
        }
    }


    private fun showContent(it: List<Country>) {
        val adapter = binding.rvCountries.adapter as CountriesAdapter
        adapter.items = it
        //To notify properly use diff util, skipped it for now
        adapter.notifyDataSetChanged()
    }

    private fun showError(it: Int) {
        val message = getString(it)
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}