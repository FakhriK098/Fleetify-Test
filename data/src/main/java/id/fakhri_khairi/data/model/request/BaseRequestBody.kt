package id.fakhri_khairi.data.model.request

import okhttp3.RequestBody

abstract class BaseRequestBody {
    abstract fun toRequestBody(): RequestBody
}