package com.herdi.yusli.glucosapp.rensponse

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("success")
	val success: RegisterData? = null
)

data class RegisterData(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
