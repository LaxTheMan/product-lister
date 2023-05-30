package com.example.productlister.ui
//
//import android.app.Activity
//import android.content.ContentValues
//import android.content.Context
//import android.content.Intent
//import android.graphics.Bitmap
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.os.Environment
//import android.provider.MediaStore
//import android.text.Editable
//import android.text.TextUtils
//import android.text.TextWatcher
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
//import android.widget.FrameLayout
//import android.widget.ImageView
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import com.example.productlister.common.Constants.ADD_PIC_REQUEST_CODE
//import com.example.productlister.common.Constants.CHANGE_PIC1_REQUEST_CODE
//import com.example.productlister.common.Constants.CHANGE_PIC2_REQUEST_CODE
//import com.example.productlister.common.Constants.CHANGE_PIC3_REQUEST_CODE
//import com.example.productlister.databinding.FragmentViewEntryBinding
//import com.google.android.material.snackbar.Snackbar
//import com.google.android.material.textfield.TextInputLayout
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.ktx.storage
//import dagger.hilt.android.AndroidEntryPoint
//import java.io.File
//import java.io.OutputStream
//import java.util.*
//
//
//@AndroidEntryPoint
//class ViewEntryFragment : Fragment() {
//
//    private lateinit var binding: FragmentViewEntryBinding
//    private val db = Firebase.firestore
//
//    //    private lateinit var currentPhotoPath: String
//    private var picNumber: Int = 0
//
//    private var bitmap1: Bitmap? = null
//    private var bitmap2: Bitmap? = null
//    private var bitmap3: Bitmap? = null
//
//    private var imageUri1: Uri? = null
//    private var imageUri2: Uri? = null
//    private var imageUri3: Uri? = null
//
//    private var imageUrl1: String? = null
//    private var imageUrl2: String? = null
//    private var imageUrl3: String? = null
//
//
////    private var resultLauncher =
////        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
////            if (result.resultCode == Activity.RESULT_OK) {
////
////                val imageFile = File(currentPhotoPath)
////
////                val imageUri = FileProvider.getUriForFile(
////                    requireContext(),
////                    "com.example.productlister.fileprovider",  // Replace with your app's FileProvider authority
////                    imageFile
////                )
////
////                Log.d("debug",imageFile.absolutePath.toString())
////                Log.d("debug",imageUri.toString())
////
////                val bitmap = BitmapFactory.decodeFile(imageUri.toString())
////
////
//////                binding.picture1.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
//////                if (binding.picture1Btn.visibility == View.GONE)
//////                    binding.picture1Btn.visibility = View.VISIBLE
////
//////                val photo = data!!.extras!!.getByteArray("data") as Bitmap?
//////                binding.picture1.setImageBitmap(photo)
//////                if (binding.picture1Btn.visibility == View.GONE)
//////                    binding.picture1Btn.visibility = View.VISIBLE
////
////            }
////        }
//
////    private var readPermissionGranted = false
////    private var writePermissionGranted = false
////    private lateinit var permissionsLauncher: ActivityResultLauncher<Array<String>>
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentViewEntryBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupTextField(binding.nameTextField, binding.nameTextLayout)
//        setupTextField(binding.priceTextField, binding.priceTextLayout)
//        setupTextField(binding.pricePerGTextField, binding.pricePerGTextLayout)
//        setupTextField(binding.netWtTextField, binding.netWtTextLayout)
//
//        binding.addPhotoLayout.setOnClickListener {
//            startCameraIntent(ADD_PIC_REQUEST_CODE)
////            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////            try {
////                val imageFile = createImageFile()
////                if (imageFile != null) {
////                    val imageUri = FileProvider.getUriForFile(
////                        requireContext(),
////                        "com.example.productlister.fileprovider",
////                        imageFile
////                    )
////                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
////
////                    openActivityForResult()
////                } else {
////                    Toast.makeText(
////                        requireContext(),
////                        "Failed to create image file",
////                        Toast.LENGTH_SHORT
////                    )
////                        .show()
////                }
////            } catch (e: java.lang.Exception) {
////                Log.d("debug", e.toString())
////            }
//        }
//
//        binding.picture1Btn.setOnClickListener {
//            startCameraIntent(CHANGE_PIC1_REQUEST_CODE)
//        }
//
//        binding.picture2Btn.setOnClickListener {
//            startCameraIntent(CHANGE_PIC2_REQUEST_CODE)
//        }
//
//        binding.picture3Btn.setOnClickListener {
//            startCameraIntent(CHANGE_PIC3_REQUEST_CODE)
//        }
//
//        binding.picture1Btn.setOnLongClickListener {
//            clearImage(binding.picture1Btn, binding.picture1)
//            true
//        }
//
//        binding.picture2Btn.setOnLongClickListener {
//            clearImage(binding.picture2Btn, binding.picture2)
//            true
//        }
//
//        binding.picture3Btn.setOnLongClickListener {
//            clearImage(binding.picture3Btn, binding.picture3)
//            true
//        }
//
//        binding.saveBtn.setOnClickListener {
//            submitEntry()
//        }
//    }
//
//    private fun setupTextField(textField: EditText, textLayout: TextInputLayout) {
//        textField.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable?) {
//                if (TextUtils.isEmpty(s)) {
//                    textLayout.error = "Field required!"
//                } else {
//                    textLayout.isErrorEnabled = false
//                    textLayout.error = null
//                }
//            }
//        })
//    }
//
//    private fun startCameraIntent(requestCode: Int) {
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(cameraIntent, requestCode)
//    }
//
////    private fun openActivityForResult() {
////        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////        resultLauncher.launch(cameraIntent)
////    }
//
//    private fun clearImage(frameLayout: FrameLayout, imageView: ImageView) {
//        frameLayout.visibility = View.GONE
//        imageView.setImageBitmap(null)
//    }
//
//
//    private fun submitEntry() {
//
//        if (TextUtils.isEmpty(binding.nameTextField.text) || TextUtils.isEmpty(binding.priceTextField.text) || TextUtils.isEmpty(
//                binding.pricePerGTextField.text
//            ) || TextUtils.isEmpty(binding.netWtTextField.text)
//        ) {
//            Toast.makeText(requireContext(), "Please fill out all details", Toast.LENGTH_SHORT)
//                .show()
//            return
//        } else if (bitmap1 == null && bitmap2 == null && bitmap3 == null) {
//            Toast.makeText(requireContext(), "Add atleast one image", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        val name = binding.nameTextField.text.toString().trim()
//        val price = binding.priceTextField.text.toString().trim().toInt()
//        val pricePerG = binding.pricePerGTextField.text.toString().trim().toFloat()
//        val netWt = binding.netWtTextField.text.toString().trim().toInt()
//
//        bitmap1?.let {
//            val imageUri1 = saveImageToGallery(it, requireContext())
//            imageUrl1 = uploadToFirebase(imageUri1)
//        }
//        bitmap2?.let {
//            val imageUri2 = saveImageToGallery(it, requireContext())
//            imageUrl2 = uploadToFirebase(imageUri2)
//        }
//        bitmap3?.let {
//            val imageUri3 = saveImageToGallery(it, requireContext())
//            imageUrl3 = uploadToFirebase(imageUri3)
//        }
//
//        val entry = hashMapOf(
//            "id" to "1",
//            "name" to name,
//            "price" to price,
//            "pricePerGram" to pricePerG,
//            "netWeight" to netWt,
//            "imageUrl1" to imageUrl1,
//            "imageUrl2" to imageUrl2,
//            "imageUrl3" to imageUrl3,
//            "dateAdded" to Date()
//        )
//
//        db.collection("entries")
//            .add(entry)
//            .addOnSuccessListener { documentReference ->
//                Log.d("firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w("firestore", "Error adding document", e)
//            }
//
//        // add data to firebase
//
//        parentFragmentManager.popBackStack()
//        Snackbar.make(binding.root, "Entry Added", Snackbar.LENGTH_SHORT).show()
//    }
//
//
//    private fun saveImageToGallery(bitmap: Bitmap, context: Context): Uri? {
//        var fos: OutputStream? = null
//        val filename = "${System.currentTimeMillis()}.jpg"
//        var imageUri: Uri? = null
//
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                val resolver = context.contentResolver
//                val contentValues = ContentValues()
//                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$filename")
//                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
//                contentValues.put(
//                    MediaStore.MediaColumns.RELATIVE_PATH,
//                    Environment.DIRECTORY_PICTURES + File.separator + "TestFolder"
//                )
//                imageUri =
//                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
//                fos = Objects.requireNonNull(imageUri)?.let { resolver.openOutputStream(it) }
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
//                Objects.requireNonNull(fos)
//                Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show()
//            }
//        } catch (e: java.lang.Exception) {
//            Toast.makeText(context, "Image not saved \n$e", Toast.LENGTH_SHORT).show()
//        }
//        Log.d("debug", imageUri.toString())
//        return imageUri
//    }
//
//    private fun uploadToFirebase(imageUri: Uri?): String? {
//        var downloadUrl: String? = null
//
//        return imageUri?.let {imageUri->
//            val storageReference = Firebase.storage.reference
//            val imagesReference = storageReference.child("images/${imageUri.lastPathSegment}")
//            val uploadTask = imagesReference.putFile(imageUri)
//            uploadTask.continueWithTask {task ->
//                if (!task.isSuccessful) {
//                    task.exception?.let {
//                        throw it
//                    }
//                }
//                imagesReference.downloadUrl
//            }.addOnCompleteListener {task ->
//                if (task.isSuccessful) {
//                    downloadUrl = task.result.toString()
//                } else {
//                    Log.d("debug",task.exception.toString())
//                }
//            }
//            downloadUrl
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == ADD_PIC_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val photo = data!!.extras!!["data"] as Bitmap?
//
//            when (picNumber) {
//                0 -> {
//                    imageUri1 = data?.data
//                    bitmap1 = photo
//                    binding.picture1.setImageBitmap(photo)
//                    if (binding.picture1Btn.visibility == View.GONE)
//                        binding.picture1Btn.visibility = View.VISIBLE
//                    picNumber++
//                }
//                1 -> {
//                    imageUri2 = data?.data
//                    bitmap2 = photo
//                    binding.picture2.setImageBitmap(photo)
//                    if (binding.picture2Btn.visibility == View.GONE)
//                        binding.picture2Btn.visibility = View.VISIBLE
//                    picNumber++
//                }
//                2 -> {
//                    imageUri3 = data?.data
//                    bitmap3 = photo
//                    binding.picture3.setImageBitmap(photo)
//                    if (binding.picture3Btn.visibility == View.GONE)
//                        binding.picture3Btn.visibility = View.VISIBLE
//                    picNumber++
//                    picNumber %= 2
//                }
//            }
////            val imageFile = File(currentPhotoPath)
////            binding.picture1.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
//        }
//
//        if (requestCode == CHANGE_PIC1_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val photo = data!!.extras!!["data"] as Bitmap?
//            imageUri1 = data?.data
//
//            binding.picture1Btn.visibility = View.VISIBLE
//            binding.picture1.setImageBitmap(photo)
//        }
//
//        if (requestCode == CHANGE_PIC2_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val photo = data!!.extras!!["data"] as Bitmap?
//            imageUri2 = data?.data
//
//            binding.picture2Btn.visibility = View.VISIBLE
//            binding.picture2.setImageBitmap(photo)
//        }
//
//        if (requestCode == CHANGE_PIC3_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val photo = data!!.extras!!["data"] as Bitmap?
//            imageUri3 = data?.data
//
//            binding.picture3Btn.visibility = View.VISIBLE
//            binding.picture3.setImageBitmap(photo)
//        }
//    }
//
////    private fun createImageFile(): File? {
////        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
////        val imageFileName = "JPEG_${timeStamp}_"
////        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
////
////        return File.createTempFile(
////            imageFileName,
////            ".jpg",
////            storageDir
////        ).apply {
////            currentPhotoPath = absolutePath
////        }
////    }
//
////    private fun updateOrRequestPermissions() {
////        val hasReadPermission = ContextCompat.checkSelfPermission(
////            requireContext(),
////            Manifest.permission.READ_EXTERNAL_STORAGE
////        ) == PackageManager.PERMISSION_GRANTED
////        val hasWritePermission = ContextCompat.checkSelfPermission(
////            requireContext(),
////            Manifest.permission.WRITE_EXTERNAL_STORAGE
////        ) == PackageManager.PERMISSION_GRANTED
////        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
////
////        readPermissionGranted = hasReadPermission
////        writePermissionGranted = hasWritePermission || minSdk29
////
////        val permissionsToRequest = mutableListOf<String>()
////        if (!writePermissionGranted) {
////            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
////        }
////        if (!readPermissionGranted) {
////            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
////        }
////        if (permissionsToRequest.isNotEmpty()) {
////            permissionsLauncher.launch(permissionsToRequest.toTypedArray())
////        }
////    }
//
////    private fun launchCameraIntent() {
////        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
////
////        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
////
////        try {
////            val imageFile = createImageFile()
////            if (imageFile != null) {
////                val imageUri = FileProvider.getUriForFile(
////                    requireContext(),
////                    requireContext().packageName,
////                    imageFile
////                )
////                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
////
////                // Launch the camera intent with the request code
////                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
////            } else {
////                Toast.makeText(requireContext(), "Failed to create image file", Toast.LENGTH_SHORT)
////                    .show()
////            }
////        } catch (e: Exception) {
////            Log.d("debug", e.toString())
////            Toast.makeText(requireContext(), "Failed to launch camera", Toast.LENGTH_SHORT).show()
//////        }
////    }
//
//
////    private fun checkPermission(): Boolean {
////        return if (SDK_INT >= Build.VERSION_CODES.R) {
////            Environment.isExternalStorageManager()
////        } else {
////            val result =
////                ContextCompat.checkSelfPermission(
////                    requireContext(),
////                    Manifest.permission.READ_EXTERNAL_STORAGE
////                )
////            val result1 =
////                ContextCompat.checkSelfPermission(
////                    requireContext(),
////                    Manifest.permission.WRITE_EXTERNAL_STORAGE
////                )
////            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
////        }
////    }
//
////    private fun requestPermission() {
////        if (SDK_INT >= Build.VERSION_CODES.R) {
////            try {
////                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
////                intent.addCategory("android.intent.category.DEFAULT")
////                intent.data = Uri.parse(
////                    String.format(
////                        "package:%s",
////                        ApplicationProvider.getApplicationContext<Context>().getPackageName()
////                    )
////                )
////                startActivityForResult(intent, 2296)
////            } catch (e: java.lang.Exception) {
////                val intent = Intent()
////                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
////                startActivityForResult(intent, 2296)
////            }
////        } else {
////            //below android 11
////            ActivityCompat.requestPermissions(
////                requireActivity(), arrayOf<String>(
////                    WRITE_EXTERNAL_STORAGE
////                ), WRITE_REQUEST_CODE
////            )
////        }
////    }
//
////    private fun hasWritePermission() =
////        EasyPermissions.hasPermissions(
////            requireContext(),
////            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
////            android.Manifest.permission.READ_EXTERNAL_STORAGE,
////        )
//
////    private fun requestWritePermission() {
////        EasyPermissions.requestPermissions(
////            this,
////            "This application cannot work",
////            CAMERA_REQUEST_CODE,
////            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
////            android.Manifest.permission.READ_EXTERNAL_STORAGE
////        )
////    }
//
////    override fun onRequestPermissionsResult(
////        requestCode: Int,
////        permissions: Array<out String>,
////        grantResults: IntArray
////    ) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
////        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
////    }
//
////    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
////        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
////            SettingsDialog.Builder(requireActivity()).build().show()
////        } else {
////            requestWritePermission()
////        }
////    }
//
////    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
////        Toast.makeText(requireContext(), "Permission Granted!", Toast.LENGTH_SHORT).show()
////        setViewVisibility()
////    }
//
////    private fun setViewVisibility() {
////        if (hasWritePermission()) {
////            Toast.makeText(requireContext(), "Has permission", Toast.LENGTH_SHORT).show()
////        } else {
////            Toast.makeText(requireContext(), "Doesn't have permission", Toast.LENGTH_SHORT).show()
////            requestWritePermission()
////        }
////    }
//}