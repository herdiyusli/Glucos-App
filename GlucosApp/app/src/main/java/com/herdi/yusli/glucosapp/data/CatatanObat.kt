package com.herdi.yusli.glucosapp.data

data class CatatanObat(
    var catatanObatPagi: Pagi,
    var catatanObatSiang: Siang,
    var catatanObatMalam: Malam
)

data class Pagi(
    val createdAt: Int,
    val Status: Boolean = false
)

data class Siang(
    val createdAt: Int,
    val Status: Boolean = false
)

data class Malam(
    val createdAt: Int,
    val Status: Boolean = false
)


