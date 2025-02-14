package com.example.covertervk.domain.use_case

import android.util.Log
import com.example.covertervk.data.remote.dto.toDomain
import com.example.covertervk.domain.model.MusicChartDomain
import com.example.covertervk.domain.repository.Repository
import com.example.covertervk.domain.util.Resource
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class getChartsUseCase @Inject constructor(
    private val repository: Repository
){
    operator fun invoke(): kotlinx.coroutines.flow.Flow<Resource<MusicChartDomain>> = flow {
        try {
            emit(Resource.Loading<MusicChartDomain>())
            val value = repository.getChart().toDomain()
            emit(Resource.Success(value))
            Log.d("111", value.toString())
        } catch(e: HttpException) {
            emit(Resource.Error<MusicChartDomain>(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch(e: IOException) {
            emit(Resource.Error<MusicChartDomain>(e.localizedMessage ?: "An unexpected error occured"))
        }

    }
}