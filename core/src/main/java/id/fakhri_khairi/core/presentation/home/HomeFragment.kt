package id.fakhri_khairi.core.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.base.BaseFragment
import id.fakhri_khairi.core.databinding.FragmentHomeBinding
import id.fakhri_khairi.core.presentation.form.CreateReportBottomSheet

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override suspend fun FragmentHomeBinding.setupEvent() {}

    override suspend fun FragmentHomeBinding.setupState() {}

    override fun FragmentHomeBinding.initBinding() {
        initListener()
    }

    private fun FragmentHomeBinding.initListener() {
        btnCreateReport.setOnClickListener {
            val formBottomSheet = CreateReportBottomSheet()
            formBottomSheet.show(childFragmentManager, formBottomSheet.tag)
        }
    }
}