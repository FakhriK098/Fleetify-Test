package id.fakhri_khairi.data.service

import id.fakhri_khairi.data.model.responses.CreateReportResponse
import id.fakhri_khairi.data.model.responses.VehicleResponse
import id.fakhri_khairi.data.model.responses.ReportResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Vehicle {

    @GET(value = "read_all_laporan")
    suspend fun getAllReport(
        @Query("userId") userId: String
    ): List<ReportResponse>

    @GET(value = "list_vehicle")
    suspend fun getAllVehicle(): List<VehicleResponse>

    @POST(value = "add_laporan")
    suspend fun createReport(@Body payload: RequestBody) : CreateReportResponse
}