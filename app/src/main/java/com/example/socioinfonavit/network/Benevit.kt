package com.example.socioinfonavit.network

data class Benevit(
    val id:Int,
    val title: String,
    val description: String,
    val instructions: String,
    val primary_color: String,
    val vector_full_path: String,
    val wallet: Wallet, val ally: BenevitExtraData,
    var isLocked : Boolean)
