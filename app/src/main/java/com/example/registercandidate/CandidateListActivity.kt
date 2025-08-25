package com.example.registercandidate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.registercandidate.adapters.CandidateListAdapter
import com.example.registercandidate.model.Candidate
import com.example.registercandidate.network.ApiClient
import com.example.registercandidate.network.ApiServiceInterface
import com.example.registercandidate.utilities.CheckInternetConnection
import com.example.votersearch.databinding.ActivityCandidateListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CandidateListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCandidateListBinding
    private var mApiService: ApiServiceInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCandidateListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mApiService = ApiClient.getClientRequestWithHeader()!!.create(ApiServiceInterface::class.java)
        getCandidates()
    }

    private fun getCandidates() {
        binding.progressbar.visibility = View.VISIBLE
        mApiService!!.getCandidates().enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 400) {
                    val errorMessage: String? = response.errorBody()?.string()
                    response.errorBody()?.let {
                        binding.progressbar.visibility = View.GONE
                        Log.e("error", it.string())
                        CheckInternetConnection.showAlertDialog(
                            it.string(),
                            this@CandidateListActivity
                        )
                    }
                    return
                }
                    if (response.code() == 200 || response.code() == 201) {
                        binding.progressbar.visibility = View.GONE
                        val stringResponse = response.body()?.string()
                        println("$stringResponse")
                        stringResponse?.let {
                           val candidateList = parseCandidateJson(it) as MutableList<Candidate>
                            val layoutManager = LinearLayoutManager(this@CandidateListActivity)
                            binding.recyclerView.layoutManager = layoutManager

                            // Step 3: Create and set the adapter with the parsed data
                            val adapter = CandidateListAdapter(candidateList)
                            binding.recyclerView.adapter = adapter
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                    } else {
                        binding.progressbar.visibility = View.GONE
                        CheckInternetConnection.showAlertDialog("Error: ${response.toString()}",this@CandidateListActivity)
                    }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                println(t.message)
                binding.progressbar.visibility = View.GONE
            }
        })
    }

    fun parseCandidateJson(jsonString: String): List<Candidate> {
        val gson = Gson()
        val listType = object : TypeToken<List<Candidate>>() {}.type
        return gson.fromJson(jsonString, listType)
    }

  /*  private fun parseJson(jsonResponse: String):ArrayList<Candidate> {
        var candidateListData = ArrayList<Candidate> ()
        val jsonObject = JSONObject(jsonResponse)
        val candidateArray = jsonObject.getJSONArray("voterList")
        val totalCount = jsonObject.get("VoterCount")
       
        for (i in 0 until candidateArray.length()) {
            val jsonObj = candidateArray.optJSONObject(i)
            val candidateID = jsonObj.getInt("CandidateID")
            val candidateNameEnglish = jsonObj.getString("CandidateNameEnglish")
            val candidateNameMarathi = jsonObj.getString("CandidateNameMarathi")
            val phNo = jsonObj.getString("PhNo")
            val address = jsonObj.getString("Address")
            val partyNameEnglish = jsonObj.getString("PartyNameEnglish")
            val partyNameMarathi = jsonObj.getString("PartyNameMarathi")
            val dob = jsonObj.getString("DOB")
            val photoPath = jsonObj.getString("PhotoPath")
            val logoPath = jsonObj.getString("LogoPath")
            val partyLogoPath = jsonObj.getString("PartyLogoPath")
            val bannerPath = jsonObj.getString("BannerPath")
            val slipPrintPath = jsonObj.getString("SlipPrintPath")
            val slipPrintMsgEnglish = jsonObj.getString("SlipPrintMsgEnglish")
            val slipPrintMsgMarathi = jsonObj.getString("SlipPrintMsgMarathi")
            val activationKey = jsonObj.getString("ActivationKey")
            val electionID = jsonObj.getInt("ElectionID")
            
           *//* val candidateData = Candidate(candidateID,candidateNameEnglish,candidateNameMarathi,
                phNo,address,partyNameEnglish,partyNameMarathi,dob,
                photoPath,logoPath,partyLogoPath,bannerPath,
                slipPrintPath,slipPrintMsgEnglish,slipPrintMsgMarathi,
                activationKey, electionID.toString()
            )*//*

            candidateListData.add(candidateData)
        }
        return candidateListData
    }*/

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