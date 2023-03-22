package id.fakhri_khairi.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseAdapter
import id.fakhri_khairi.core.databinding.ItemComplaintReportBinding
import id.fakhri_khairi.core.misc.toFormattedDate
import id.fakhri_khairi.domain.Report

class ReportAdapter : BaseAdapter<Report>() {

    private class ReportViewHolder(
        val binding: ItemComplaintReportBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private fun ItemComplaintReportBinding.bind(report: Report) {
        tvReportId.text = report.reportId
        tvReportDate.text = report.createdAt.toFormattedDate("yyyy-MM-dd HH:mm:ss", "EEEE, dd MMM - HH:mm")
        tvReportStatus.text = report.reportStatus
        tvVehicleName.text = report.vehicleName
        tvVehicleId.text = report.vehicleLicenseNumber
        tvReportBy.text = report.createdBy
        tvComplaintNoteValue.text = report.note
        ivReportImg.load(report.photo) {
            error(R.drawable.ic_image_24)
        }
    }

    override fun getViewType(position: Int): Int = BASE_VIEW_TYPE

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReportViewHolder(
            ItemComplaintReportBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReportViewHolder) {
            val report = items[position]
            report?.let { holder.binding.bind(it) }
        }
    }

    companion object {
        private const val BASE_VIEW_TYPE = 0
    }
}