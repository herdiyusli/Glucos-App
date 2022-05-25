package com.herdi.yusli.glucosapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alarm(
    var jamPagi: String,
    var jamSiang: String,
    var jamMalam: String
) : Parcelable

