package com.example.registercandidate.model

import java.io.Serializable


data class AdvanceSearchData(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val fromAge: String,
    val toAge: String,
    val mobileNo: String,
    val partNo: String,
    val voterId: String,
    val houseNo: String
) : Serializable