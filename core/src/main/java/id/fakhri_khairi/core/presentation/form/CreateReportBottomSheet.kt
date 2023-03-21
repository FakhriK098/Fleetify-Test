package id.fakhri_khairi.core.presentation.form

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import id.fakhri_khairi.core.R
import id.fakhri_khairi.core.base.BaseDialogFragment
import id.fakhri_khairi.core.databinding.BottomSheetCreateReportBinding
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class CreateReportBottomSheet : BaseDialogFragment<BottomSheetCreateReportBinding>() {

    private lateinit var binding: BottomSheetCreateReportBinding
    private val vehicleList : MutableList<String> = mutableListOf("A","B","C","D","E")
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

    override suspend fun BottomSheetCreateReportBinding.setupEvent() {}

    override suspend fun BottomSheetCreateReportBinding.setupState() {}

    override fun BottomSheetCreateReportBinding.initBinding() {
        initCurrentDate()
        initAdapter()
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        easyImage.handleActivityResult(requestCode,resultCode, data, requireActivity(), object:
            DefaultCallback() {
            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                val photo = imageFiles[0].file
                binding.ivReportImg.load(photo) {
                    error(R.drawable.ic_image_24)
                }
            }

        })
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