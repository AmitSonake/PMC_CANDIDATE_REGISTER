package com.example.registercandidate

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.registercandidate.model.Candidate
import com.example.registercandidate.network.ApiClient
import com.example.registercandidate.network.ApiServiceInterface
import com.example.registercandidate.utilities.CheckInternetConnection
import com.example.votersearch.databinding.ActivityRegisterCandidateBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class EditCandidate : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterCandidateBinding
    private var mApiService: ApiServiceInterface? = null
    private var candidateObject: Candidate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterCandidateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val candidate: Candidate? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("candidate_object", Candidate::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("candidate_object")
        }
        // Now you can use the 'candidate' object to populate your views
        if (candidate != null) {
            // For example, set a TextView with the candidate's name
            // val nameTextView: TextView = findViewById(R.id.nameTextView)
            // nameTextView.text = candidate.candidateNameEnglish
            candidateObject = candidate
            setFormUI()
        }
        mApiService = ApiClient.getClientRequestWithHeader()!!.create(ApiServiceInterface::class.java)

        binding.registerButton.setOnClickListener {
            registerCandidate()
        }

        binding.DOB.setOnClickListener {
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.DOB.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        binding.uploadLogoButton.setOnClickListener {
            /*if (fileUri != null) {
                uploadImage()
            } else {
                Toast.makeText(
                    applicationContext, "Please Select Image to Upload",
                    Toast.LENGTH_LONG
                ).show()
            }*/
        }
    }

    private fun setFormUI(){
        if(candidateObject!=null){
            binding.candidateNameEnglish.setText(candidateObject!!.CandidateNameEnglish)
            binding.candidateNameMarathi.setText(candidateObject!!.CandidateNameMarathi)
            binding.PhoneNo.setText(candidateObject!!.PhNo)
            binding.Address.setText(candidateObject!!.Address)
            binding.PartNameEnglish.setText(candidateObject!!.PartyNameEnglish)
            binding.PartyNameMarathi.setText(candidateObject!!.PartyNameMarathi)
            binding.DOB.setText(candidateObject!!.DOB)
            binding.SliPrintEnglish.setText(candidateObject!!.SlipPrintMsgEnglish)
            binding.SliPrintMarathi.setText(candidateObject!!.SlipPrintMsgMarathi)
            binding.activationKey.setText(candidateObject!!.ActivationKey)
        }
    }

    private fun registerCandidate() {
        val candidateNameEnglish = binding.candidateNameEnglish.text.toString()
        val candidateNameMarathi = binding.candidateNameMarathi.text.toString()
        val phNo = binding.PhoneNo.text.toString()
        val address = binding.Address.text.toString()
        val partyNameEnglish = binding.PartNameEnglish.text.toString()
        val partyNameMarathi = binding.PartyNameMarathi.text.toString()
        val dob = binding.DOB.text.toString()
        val photoPath :String? = candidateObject?.PhotoPath
        val logoPath :String? = candidateObject?.LogoPath
        val partyLogoPath :String? = candidateObject?.PartyLogoPath
        val bannerPath :String? = candidateObject?.BannerPath
        val slipPrintPath  :String? = candidateObject?.SlipPrintPath
        val sliPrintEnglish = binding.SliPrintEnglish.text.toString()
        val sliPrintMarathi = binding.SliPrintMarathi.text.toString()
        val activationKey = binding.activationKey.text.toString()
        val electionID = 1
        val candidateId = candidateObject?.CandidateId ?: 0
        binding.progressbar.visibility = View.VISIBLE
        mApiService!!.registerCandidate(
            candidateId,candidateNameEnglish,candidateNameMarathi,phNo,address,
            partyNameEnglish,partyNameMarathi,dob,
            photoPath, logoPath,partyLogoPath,bannerPath,slipPrintPath,
            sliPrintEnglish,
            sliPrintMarathi,activationKey, candidateObject?.ElectionId ?: 1
        ).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 400) {
                    val errorMessage: String? = response.errorBody()?.string()
                    response.errorBody()?.let {
                        binding.progressbar.visibility = View.GONE
                        Log.e("error", errorMessage.toString())
                        CheckInternetConnection.showAlertDialog(
                            it.string(),
                            this@EditCandidate
                        )
                    }
                    return
                }
                    if (response.code() == 200 || response.code() == 201) {
                        binding.progressbar.visibility = View.GONE
                        val stringResponse = response.body()?.string()
                        println("$stringResponse")
                        stringResponse?.let {
                            showSuccessDialog(this@EditCandidate)
                            //Toast.makeText(this@RegisterCandidate, "Candidate inserted successfully", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        binding.progressbar.visibility = View.GONE
                        CheckInternetConnection.showAlertDialog("Error: ${response.toString()}",this@EditCandidate)
                    }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(t.message)
                binding.progressbar.visibility = View.GONE
            }
        })
    }

    // This function can be called from your registration success callback
    fun showSuccessDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Registration Successful")
        builder.setMessage("User inserted successfully!")
        builder.setCancelable(false) // Prevents the dialog from being dismissed by tapping outside or pressing the back button

        // "Insert More" Button
        builder.setPositiveButton("Insert More") { dialog, which ->
            // Handle "Insert More" button click
            // For example, you could clear the form fields or navigate to the registration screen
            // In this example, we'll just dismiss the dialog and do nothing else.
            dialog.dismiss()
        }

        // "Go Home" Button
        builder.setNegativeButton("Go Home") { dialog, which ->
            // Handle "Go Home" button click
            // For example, you could navigate to the main activity or home screen
            /*val intent = Intent(context, MainActivity::class.java) // Replace MainActivity with your home activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)*/
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.data != null) {
             fileUri = data.data
             try {
                 val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
                 image_view.setImageBitmap(bitmap)

             } catch (e: Exception) {
                 Log.e("Exception", "Error: " + e)
             }
         }
     }

     fun uploadImage() {
         if (fileUri != null) {
             val progressDialog = ProgressDialog(this)
             progressDialog.setTitle("Uploading Image...")
             progressDialog.setMessage("Processing...")
             progressDialog.show()

             val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                 .child("images")
             ref.putFile(fileUri!!).addOnSuccessListener {
                 progressDialog.dismiss()
                 Toast.makeText(applicationContext, "File Uploaded Successfully", Toast.LENGTH_LONG)
                     .show()
             }.addOnFailureListener {
                 progressDialog.dismiss()
                 Toast.makeText(applicationContext, "File Upload Failed...", Toast.LENGTH_LONG)
                     .show()
             }
         }
     }

     fun retrive_image() {
         val storageReference: StorageReference = FirebaseStorage.getInstance().reference
         val image_refrance: StorageReference = storageReference.child("images")

         val progressDialog = ProgressDialog(this)
         progressDialog.setTitle("Retriving Image...")
         progressDialog.setMessage("Processing...")
         progressDialog.show()

         image_refrance.downloadUrl.addOnSuccessListener { uri: Uri ->

             Glide.with(this@MainActivity)
                 .load(uri)
                 .into(image_view)

             progressDialog.dismiss()
             Toast.makeText(this,"Image Retrived Successfull",Toast.LENGTH_LONG).show()
         }
             .addOnFailureListener { exception ->
                 progressDialog.dismiss()
                 Toast.makeText(this,"Image Retrived Failed: "+exception.message,Toast.LENGTH_LONG).show()

             }
     }

 */
}