package com.example.registercandidate.model

import java.io.Serializable

data class VoterData(
    val VoterID : String,
    val PartNo : String,
    val SlNoInPart : String,
    val SectionNo : String,
    val HNo : String,
    val FullNameMarathi : String,
    val FullNameEnglish : String,
    val FirstNameEnglish : String,
    val FirstNameMarathi: String,
    val LastNameMarathi : String,
    val LastNameEnglish : String,
    val Age: String,
    val Gender : String,
    val RelationFirstNameEnglish : String,
    val RelationFirstNameMarathi : String,
    val RelationLastNameEnglish : String,
    val RelationLastNameMarathi : String,
    val EpicNo : String,
    val VAddressMarathi : String,
    val VAddressEnglish : String,
    val BoothNameMarathi : String,
    val BoothNameEnglish : String,
) : Serializable



data class VoterDetails(
    val VoterId: Int,
    val AcNo: Int,
    val PartNo: Int,
    val SlNoInPart: Int,
    val SectionNo: Int,
    val HNo: String,
    val FullNameMarathi: String,
    val FullNameEnglish: String,
    val FirstNameEnglish: String,
    val FirstNameMarathi: String,
    val LastNameEnglish: String,
    val LastNameMarathi: String,
    val Age: Int,
    val Gender: String,
    val RelationType: String,
    val RelationFirstNameEnglish: String,
    val RelationFirstNameMarathi: String,
    val RelationLastNameEnglish: String,
    val RelationLastNameMarathi: String,
    val EpicNo: String,
    val VAddressMarathi: String,
    val VAddressEnglish: String,
    val BoothId: Int,
    val BoothNameMarathi: String,
    val BoothNameEnglish: String
) : Serializable


