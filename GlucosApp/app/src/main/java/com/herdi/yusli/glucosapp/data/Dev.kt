package com.herdi.yusli.glucosapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dev(
    var name: String,
    var role: String,
    var photo: Int,
    var email: String
) : Parcelable
