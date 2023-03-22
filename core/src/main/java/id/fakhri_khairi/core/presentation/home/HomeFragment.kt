package id.fakhri_khairi.core.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentHomeBinding
import id.fakhri_khairi.core.presentation.adapter.ReportAdapter
import id.fakhri_khairi.core.presentation.form.CreateReportBottomSheet
import id.fakhri_khairi.domain.Report
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()
    private val reportList : MutableList<Report> = mutableListOf()
    private var reportAdapter = ReportAdapter()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentHomeBinding.setupEvent() {
        viewModel.event.collect {
            when(it) {
                is HomeEvent.Success -> {
                    renderLoading(false)
                    renderData(it.data)
                }
                is HomeEvent.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    renderLoading(false)
                }
                HomeEvent.Loading -> renderLoading()
            }
        }
    }

    override suspend fun FragmentHomeBinding.setupState() {}

    override fun FragmentHomeBinding.initBinding() {
        viewModel.getAllReport()
        initAdapter()
        initListener()
    }

    private fun FragmentHomeBinding.initListener() {
        btnCreateReport.setOnClickListener {
            val formBottomSheet = CreateReportBottomSheet(
                onSuccessCreate = { viewModel.getAllReport() }
            )
            formBottomSheet.show(childFragmentManager, formBottomSheet.tag)
        }
    }

    private fun FragmentHomeBinding.initAdapter() {
        rvComplaintReport.apply {
            adapter = reportAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun renderData(data: List<Report>) {
        reportList.clear()
        reportList.addAll(data)
        reportAdapter.setItems(reportList)
    }

    private fun FragmentHomeBinding.renderLoading(isLoading: Boolean = true) {
        rvComplaintReport.isVisible = !isLoading
        icSkeleton.root.isVisible = isLoading
    }
}