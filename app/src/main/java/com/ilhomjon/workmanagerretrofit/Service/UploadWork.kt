package com.ilhomjon.workmanagerretrofit.Service

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ilhomjon.workmanagerretrofit.Models.User
import com.ilhomjon.workmanagerretrofit.Retrofit.ApiClient
import com.ilhomjon.workmanagerretrofit.Retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpLoadWork(val context: Context, workerParameters: WorkerParameters)
    : Worker(context, workerParameters){
    private val TAG = "UpLoadWork"
    override fun doWork(): Result {

        getUsers()

        return Result.success()
    }

    private fun getUsers() {
        ApiClient.getRetrofit().create(ApiService::class.java)
            .getUsers().enqueue(object : Callback<List<User>>{
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        body?.forEach {
                            Log.d(TAG, "onResponse: $it")
                            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {

                }
            })
    }

}