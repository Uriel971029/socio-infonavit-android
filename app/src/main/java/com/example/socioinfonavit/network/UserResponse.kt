package com.example.socioinfonavit.network

data class UserResponse (val id : Int, val email: String, val role:  String, val member: MemberResponse, val sign_in_count : Int)