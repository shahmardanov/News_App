package com.alijan.newsapp.api

class ApiClient {
    companion object {
        fun createApi(): ApiService{
            return RetrofitClient.createRetrofit().create(ApiService::class.java)
        }
    }
}