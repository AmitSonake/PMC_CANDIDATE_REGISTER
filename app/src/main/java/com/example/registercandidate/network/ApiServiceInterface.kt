package com.example.registercandidate.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceInterface {

//    @GET("api/getvoters")
//    fun getVoters(@Query("pageindex") pageIndex: Int,
//                  @Query("pagesize") pageSize: Int,
//                  @Query("Search_Data") searchData: String): Call<ResponseBody>
    @GET("api/Candidate")
    fun getCandidates(): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api/Candidate")
    fun registerCandidate(@Field("CandidateID") candidateID :Int,
                          @Field("CandidateNameEnglish") candidateNameEnglish :String?,
                          @Field("CandidateNameMarathi") candidateNameMarathi :String?,
                          @Field("PhNo") phNo :String?,
                          @Field("Address") address :String?,
                          @Field("PartyNameEnglish") partyNameEnglish :String?,
                          @Field("PartyNameMarathi") PartyNameMarathi :String?,
                          @Field("DOB") dob :String?,
                          @Field("PhotoPath") photoPath :String?,
                          @Field("LogoPath") logoPath :String?,
                          @Field("PartyLogoPath") partyLogoPath :String?,
                          @Field("BannerPath") bannerPath :String?,
                          @Field("SlipPrintPath") slipPrintPath :String?,
                          @Field("SlipPrintMsgEnglish") slipPrintMsgEnglish :String?,
                          @Field("SlipPrintMsgMarathi") slipPrintMsgMarathi :String?,
                          @Field("ActivationKey") activationKey :String?,
                          @Field("ElectionID") electionID :Int): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api/LogSlipShare")
    fun logSlipShare(@Field("ByMobileNo") byMobileno :String,
                        @Field("VoterId") voterId :String,
                        @Field("SharedBy") sharedVia: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("api/AppUser")
    fun registerAppUser(@Field("AppUserId") voterId :Int,
                 @Field("AppUserName") appUserName :String,
                 @Field("MobileNo") mobileNo: String,
                 @Field("ActivationKey")activationKey: String): Call<ResponseBody>

    @GET("api/getVoterDetails")
    fun getVoterDetails(@Query("voterid") voterId : String,
                ): Call<ResponseBody>

    @GET("api/getCandidateDetailsByActivationKey")
    fun getCandidateDetailsByActivationKey(@Query("activationKey") activationKey : String): Call<ResponseBody>

    @GET("api/searchVoters")
    fun searchVoters(@Query("pageindex") pageIndex : Int,
                  @Query("pagesize") pageSize : Int,
                  @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getVotersByTown")
    fun getVotersByTown(@Query("pageindex") pageIndex : Int,
                     @Query("pagesize") pageSize : Int,
                     @Query("TownName") townName : String, @Query("Search_Data") searchData : String
                        ): Call<ResponseBody>

    @GET("api/getVotersBySurname")
    fun getVotersBySurname(@Query("pageindex") pageIndex : Int,
                        @Query("pagesize") pageSize : Int,
                        @Query("Surname") surname : String,
                           @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getVotersByGender")
    fun getVotersByGender(@Query("pageindex") pageIndex : Int,
                           @Query("pagesize") pageSize : Int,
                           @Query("Gender") gender : String,
                          @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getVotersByAge")
    fun getVotersByAge(@Query("pageindex") pageIndex : Int,
                           @Query("pagesize") pageSize : Int,
                           @Query("AgeGroup") ageGroup : String,
                       @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getVotersByPartNo")
    fun getVotersByPartNo(@Query("pageindex") pageIndex : Int,
                           @Query("pagesize") pageSize : Int,
                           @Query("PartNo") partNo : String,
                          @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getVotersByBoothId")
    fun getVotersByBoothId(@Query("pageindex") pageIndex : Int,
                           @Query("pagesize") pageSize : Int,
                           @Query("BoothId") boothId : String,
                           @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/advanceSearchVoters")
    fun advanceSearchVoters(@Query("pageindex") pageIndex : Int,
                     @Query("pagesize") pageSize : Int,
                     @Query("FirstName") firstName : String,
                            @Query("MiddleName") middleName : String,
                            @Query("LastName") lastName : String,
                            @Query("FromAge") fromAge : String,
                            @Query("ToAge") toAge : String,
                            @Query("MobileNo") mobileNo : String,
                            @Query("PartNo") partNo : String,
                            @Query("EpicNo") epicNo : String,
                            @Query("HouseNo") houseNo : String): Call<ResponseBody>

    @GET("api/getTownWiseVoterCount")
    fun getTownWiseVoterCount(@Query("pageindex") pageIndex : Int,
                        @Query("pagesize") pageSize : Int,
                        @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getSurnameWiseVoterCount")
    fun getSurnameWiseVoterCount(@Query("pageindex") pageIndex : Int,
                           @Query("pagesize") pageSize : Int,
                           @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getGenderWiseVoterCount")
    fun getGenderWiseVoterCount(@Query("pageindex") pageIndex : Int,
                          @Query("pagesize") pageSize : Int,
                          @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getAgeWiseVoterCount")
    fun getAgeWiseVoterCount(@Query("pageindex") pageIndex : Int,
                       @Query("pagesize") pageSize : Int,
                       @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getPartNoWiseVoterCount")
    fun getPartNoWiseVoterCount(@Query("pageindex") pageIndex : Int,
                          @Query("pagesize") pageSize : Int,
                          @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getBoothWiseVoterCount")
    fun getBoothWiseVoterCount(@Query("pageindex") pageIndex : Int,
                           @Query("pagesize") pageSize : Int,
                           @Query("Search_Data") searchData : String): Call<ResponseBody>

    @GET("api/getVotersWithSameAddress")
    fun getVotersWithSameAddress(@Query("pageindex") pageIndex : Int,
                               @Query("pagesize") pageSize : Int,
                               @Query("voterId") voterId : String,
                                 @Query("Search_Data") searchData : String= ""): Call<ResponseBody>

    @GET("api/getVotersOnSameBooth")
    fun getVotersOnSameBooth(@Query("pageindex") pageIndex : Int,
                                 @Query("pagesize") pageSize : Int,
                                 @Query("voterId") voterId : String,
                                @Query("Search_Data") searchData : String= ""): Call<ResponseBody>

    @GET("api/getFamilyVoters")
    fun getFamilyVoters(@Query("pageindex") pageIndex : Int,
                             @Query("pagesize") pageSize : Int,
                             @Query("voterId") voterId : String,
                        ): Call<ResponseBody>

    @GET("api/GetCOCFlag")
    fun getCOCFlag(@Query("activationKey") pageIndex : String): Call<ResponseBody>

    @GET("api/GetAppUserProfileByMobileNo")
    fun getAppUserProfileByMobileNo(@Query("mobileno") mobileno : String): Call<ResponseBody>

}


