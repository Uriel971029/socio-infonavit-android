package com.example.socioinfonavit.network

data class MemberResponse(
    val id : Int,
    val user_id: Int,
    val id_socio_infonavit: String,
    val name : String,
    val lastname : String)
