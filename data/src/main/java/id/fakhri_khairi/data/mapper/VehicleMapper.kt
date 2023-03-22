package id.fakhri_khairi.data.mapper

import id.fakhri_khairi.data.model.responses.VehicleResponse
import id.fakhri_khairi.domain.Vehicle

class VehicleMapper : Mapper<VehicleResponse, Vehicle> {
    override fun convert(from: VehicleResponse): Vehicle {
        return Vehicle(
            vehicleId = from.vehicleId,
            licenseNumber = from.licenseNumber,
            type = from.type
        )
    }
}