
package com.example.registercandidate.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Candidate(
    val CandidateId: Int,
    val CandidateNameEnglish: String,
    val CandidateNameMarathi: String,
    val PhNo: String,
    val Address: String,
    val PartyNameEnglish: String,
    val PartyNameMarathi: String,
    val DOB: String,
    val PhotoPath: String?,
    val LogoPath: String?,
    val PartyLogoPath: String?,
    val BannerPath: String?,
    val SlipPrintPath: String?,
    val SlipPrintMsgEnglish: String,
    val SlipPrintMsgMarathi: String,
    val ActivationKey: String,
    val ElectionId: Int
): Parcelable