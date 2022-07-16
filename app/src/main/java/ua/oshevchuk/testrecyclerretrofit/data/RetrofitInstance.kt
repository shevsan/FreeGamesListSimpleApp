package ua.oshevchuk.testrecyclerretrofit.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ua.oshevchuk.testrecyclerretrofit.api.Api

object RetrofitInstance {
    val api: Api by lazy {
        Retrofit.Builder().baseUrl("https://www.freetogame.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
    }
}