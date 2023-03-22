package id.fakhri_khairi.core.presentation.form

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseDialogFragment
import id.fakhri_khairi.core.databinding.BottomSheetCreateReportBinding
import id.fakhri_khairi.domain.Vehicle
import kotlinx.coroutines.flow.collect
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class CreateReportBottomSheet(
    private val onSuccessCreate: () -> Unit
) : BaseDialogFragment<BottomSheetCreateReportBinding>() {

    private val viewModel by viewModels<CreateReportViewModel>()
    private lateinit var binding: BottomSheetCreateReportBinding
    private val vehicleList : MutableList<String> = mutableListOf()
    private val vehicles : MutableList<Vehicle> = mutableListOf()
    private var vehicleSelected : Vehicle? = null
    private var photoSelected: File? = null

    private val cameraPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        when {
            it.getOrDefault(Manifest.permission.CAMERA, false) -> {
                easyImage.openChooser(this@CreateReportBottomSheet)
            }
        }
    }

    @Inject
    lateinit var easyImage: EasyImage

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetCreateReportBinding {
        binding = BottomSheetCreateReportBinding.inflate(inflater, container, false)
        return binding
    }

    override suspend fun BottomSheetCreateReportBinding.setupEvent() {
        viewModel.event.collect {
            when(it) {
                is CreateReportEvent.Error -> {
                    renderLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                }
                CreateReportEvent.Loading -> renderLoading()
                is CreateReportEvent.Success -> {
                    renderLoading(false)
                    renderSuccess(it.data)
                }
                CreateReportEvent.SuccessCreateReport -> {
                    renderLoading(false)
                    onSuccessCreate.invoke()
                    dismiss()
                }
            }
        }
    }

    override suspend fun BottomSheetCreateReportBinding.setupState() {}

    override fun BottomSheetCreateReportBinding.initBinding() {
        viewModel.getAllVehicle()
        initListener()
    }

    private fun BottomSheetCreateReportBinding.initListener() {
        btnTakePicture.setOnClickListener {
            if (allPermissionsGranted()) {
                easyImage.openChooser(this@CreateReportBottomSheet)
            } else {
                cameraPermissionRequest.launch(
                    REQUIRED_PERMISSIONS
                )
            }
        }

        btnSendReport.setOnClickListener {
            verify()
        }
    }

    private fun BottomSheetCreateReportBinding.verify() {
        if (vehicleSelected == null || etComplaintNote.text.isEmpty() || photoSelected == null) {
            Toast.makeText(requireContext(), getString(R.string.title_waring_to_complete), Toast.LENGTH_SHORT)
            return
        }

        vehicleSelected.let {
            viewModel.createRecord(
                vehicleId = it!!.vehicleId,
                note = etComplaintNote.text.toString(),
                photoFile = photoSelected!!
            )
        }
    }

    private fun BottomSheetCreateReportBinding.initCurrentDate() {
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM - HH:mm")
        val current = LocalDateTime.now().format(formatter)

        tvCurrentDateTime.text = current
    }

    private fun BottomSheetCreateReportBinding.initAdapter() {
        val vehicleAdapter: ArrayAdapter<String> = object : ArrayAdapter<String>(
            root.context,
            R.layout.component_spinner,
            vehicleList
        ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val textView : TextView = super.getDropDownView(position, convertView, parent) as TextView
                if (position == 0) {
                    textView.setTextColor(Color.LTGRAY)
                }
                return textView
            }

            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }
        }

        spVehicle.adapter = vehicleAdapter
        spVehicle.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        vehicleSelected = vehicles[position-1]
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        easyImage.handleActivityResult(requestCode,resultCode, data, requireActivity(), object:
            DefaultCallback() {
            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                photoSelected = imageFiles[0].file
                binding.ivReportImg.load(photoSelected) {
                    error(R.drawable.ic_image_24)
                }
            }

        })
    }

    private fun BottomSheetCreateReportBinding.renderLoading(isLoading: Boolean = true) {
        cvCurrentDate.isVisible = !isLoading
        cvVehicle.isVisible = !isLoading
        cvReportNote.isVisible = !isLoading
        tvComplaintDocument.isVisible = !isLoading
        cvReportImg.isVisible = !isLoading
        btnTakePicture.isVisible = !isLoading
        btnSendReport.isVisible = !isLoading
        icSkeleton.root.isVisible = isLoading
    }

    private fun BottomSheetCreateReportBinding.renderSuccess(data: List<Vehicle>) {
        vehicleList.clear()
        vehicles.clear()
        vehicles.addAll(data)
        vehicleList.add("Pilih Kendaraan")
        vehicleList.addAll(data.map { vehicle -> vehicle.type })
        initAdapter()
        initCurrentDate()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireActivity(),
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}