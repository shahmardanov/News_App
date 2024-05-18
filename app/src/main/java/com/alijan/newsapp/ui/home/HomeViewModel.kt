package com.alijan.newsapp.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alijan.newsapp.api.ApiClient
import com.alijan.newsapp.model.ArticleModel
import com.alijan.newsapp.model.NewsResponseModel
import com.alijan.newsapp.util.toastError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val api = ApiClient.createApi()

    private var _newsList = MutableLiveData<List<ArticleModel>>()
    val newsList: LiveData<List<ArticleModel>> get() = _newsList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        requestAllNews()
    }

    private fun requestAllNews(query: String = "Russia"){
        _isLoading.value = true
        api.getAllNews(query).enqueue(object : Callback<NewsResponseModel>{
            override fun onResponse(
                call: Call<NewsResponseModel>,
                response: Response<NewsResponseModel>
            ) {
                val response = response.body()
                response?.let {
                    it.articles.let { it2->
                        _newsList.value = it2
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
                _errorMessage.value = t.localizedMessage
            }

        })
        _isLoading.value = false
    }
}