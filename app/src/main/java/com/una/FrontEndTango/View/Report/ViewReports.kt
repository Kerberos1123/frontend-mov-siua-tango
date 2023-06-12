package com.una.FrontEndTango.View.Report

//IMPORTS VIEJOS
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.una.FrontEndTango.ViewModel.ReportViewModel
import com.una.FrontEndTango.ViewModel.ReportViewModelFactory

//IMPORTS NUEVOS
import com.una.FrontEndTango.databinding.FragmentViewReportsBinding
import com.una.FrontEndTango.Adapter.ReportAdapter
import com.una.FrontEndTango.ViewModel.StateReport

class ViewReports : Fragment() {

    private var _binding: FragmentViewReportsBinding? = null
    private val binding get() = _binding!!

    private val reportViewModel: ReportViewModel by activityViewModels{
        ReportViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adapter = ReportAdapter()

        _binding = FragmentViewReportsBinding.inflate(inflater,container,false)

        binding.recycler.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        binding.recycler.layoutManager = layoutManager

        // Traemos todas los reports del webservice
        reportViewModel.findAllReport()
        reportViewModel.state.observe(viewLifecycleOwner) { state ->

            when (state) {
                StateReport.Loading -> {
                    // TODO: If you need do something in loading
                }
                is StateReport.Error -> {
                    // TODO: If you need do something in error
                }
                is StateReport.SuccessList -> {
                    state.reportList?.let { adapter.setReportList(it) }
                }
                else -> {
                    // TODO: Not state loaded
                }
            }

        }

        return binding.root

    }
}