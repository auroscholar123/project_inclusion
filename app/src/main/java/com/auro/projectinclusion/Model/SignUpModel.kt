package com.auro.projectinclusion.Model

data class SignUpModel(var status:String?=null,var response:Response?=null,var errorCode:String?=null,var message:String?=null)
data class Response(var id:String?=null)