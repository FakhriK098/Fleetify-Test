package id.fakhri_khairi.data.model.request

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

data class CreateReportRequest(
    val vehicleId: String,
    val note: String = "",
    val userId: String,
    val photoFile: File
) : BaseRequestBody() {
    override fun toRequestBody(): RequestBody {
        val requestFile = photoFile.asRequestBody("image/*".toMediaType())

        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(name = "vehicleId", value = vehicleId)
            .addFormDataPart(name = "note", value = note)
            .addFormDataPart(name = "userId", value = userId)
            .addFormDataPart(name = "photo", filename = photoFile.name, body = requestFile)
            .build()
    }

}
