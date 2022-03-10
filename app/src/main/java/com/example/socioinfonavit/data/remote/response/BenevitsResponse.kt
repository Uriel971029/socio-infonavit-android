package com.example.socioinfonavit.data.remote.response

import com.example.socioinfonavit.data.local.Benevit

data class BenevitsResponse(val locked: List<Benevit>, val unlocked : List<Benevit>)
