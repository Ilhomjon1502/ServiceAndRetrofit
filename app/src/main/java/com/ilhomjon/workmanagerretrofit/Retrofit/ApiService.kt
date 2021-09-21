package com.ilhomjon.workmanagerretrofit.Retrofit

import com.ilhomjon.workmanagerretrofit.Models.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers():Call<List<User>>
}