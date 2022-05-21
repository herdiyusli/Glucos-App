package com.herdi.yusli.glucosapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Diabetes(
    var tipe: String,
    var desc: String,
    var expandable: Boolean = false
) : Parcelable
