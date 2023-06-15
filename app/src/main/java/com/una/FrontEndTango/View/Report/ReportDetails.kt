package com.una.FrontEndTango.View.Report

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.una.FrontEndTango.R
import com.una.FrontEndTango.databinding.FragmentReportDetailsBinding
import androidx.core.os.bundleOf
import com.una.FrontEndTango.Adapter.ReportAdapter
import com.una.FrontEndTango.Adapter.ReportAdapter.Companion.REPORT_ID
import com.una.FrontEndTango.ViewModel.*


class ReportDetails : Fragment() {

    // Definition of the binding variable
    private var _binding: FragmentReportDetailsBinding? = null
    private val binding get() = _binding!!

    // Shared view model
    private val reportViewModel : ReportViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReportDetailsBinding.inflate(inflater,container,false)

        val reportId : String = arguments?.getString(REPORT_ID) ?: "0"

        reportViewModel.state.observe(viewLifecycleOwner){ state ->
            // this lets us avoid repeating 'binding.frameNews' before everything
            with(binding.root) {
                when (state) {
                    // just checking equality because Loading is a -singleton object instance-
                    StateReport.Loading -> {
                        // TODO: If you need do something in loading
                    }
                    // Error and Success are both -classes- so we need to check their type with 'is'
                    is StateReport.Error -> {
                        // TODO: If you need do something in error
                    }
                    is StateReport.Success -> {
                        state.report?.let {
                            binding.textUserName2.text = it.title.toString()
                            binding.editTextTextMultiLine2.setText(it.description.toString())
                        }
                    }
                    else -> {
                        // TODO: Not state loaded
                    }
                }

            }
        }

        reportViewModel.getReport(reportId.toLong())

        return binding.root
    }


}