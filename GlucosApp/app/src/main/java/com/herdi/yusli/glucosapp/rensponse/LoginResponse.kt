package com.herdi.yusli.glucosapp.rensponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("success")
	val loginData: LoginData? = null
)

data class LoginData(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("token")
	val token: String
)
