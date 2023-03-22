package id.fakhri_khairi.data.mapper

import id.fakhri_khairi.data.model.responses.ReportResponse
import id.fakhri_khairi.domain.Report

class ReportMapper : Mapper<ReportResponse, Report> {
    override fun convert(from: ReportResponse): Report {
        return Report(
            reportId = from.reportId,
            vehicleId = from.vehicleId,
            vehicleLicenseNumber = from.vehicleLicenseNumber,
            vehicleName = from.vehicleName,
            note = from.note,
            photo = from.photo,
            reportStatus = from.reportStatus,
            createdAt = from.createdAt,
            createdBy = from.createdBy
        )
    }
}