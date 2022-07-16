package ua.oshevchuk.testrecyclerretrofit.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ua.oshevchuk.testrecyclerretrofit.data.RetrofitInstance
import ua.oshevchuk.testrecyclerretrofit.models.GameModelItem
import java.io.IOException

class MainViewModel: ViewModel() {
    private val games = MutableLiveData<List<GameModelItem>>()
    fun getGamesList(): LiveData<List<GameModelItem>> {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getGamesList()

            }catch (e:IOException){
                return@launch
            }catch (e:HttpException){
                return@launch
            }
            if (response.isSuccessful&&response.body()!=null){
                games.value = response.body()
                return@launch
            }
        }
        return games
    }

}