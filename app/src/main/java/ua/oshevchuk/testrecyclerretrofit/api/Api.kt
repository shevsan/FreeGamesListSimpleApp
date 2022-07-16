package ua.oshevchuk.testrecyclerretrofit.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import ua.oshevchuk.testrecyclerretrofit.models.GameModelItem

interface Api {
    @GET("./api/games")
    suspend fun getGamesList():Response<List<GameModelItem>>

}