package com.example.covertervk.domain.use_case

import com.example.covertervk.data.remote.dto.toDomain
import com.example.covertervk.domain.model.TrackDomain
import com.example.covertervk.domain.repository.Repository
import com.example.covertervk.domain.util.Resource
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class getTrackByIdUseCase @Inject constructor(
    private val repository: Repository
){
    operator fun invoke(id: String): kotlinx.coroutines.flow.Flow<Resource<TrackDomain>> = flow {
        try {
            emit(Resource.Loading<TrackDomain>())
            val value = repository.getTrackById(id).toDomain()
            emit(Resource.Success(value))
        } catch(e: HttpException) {
            emit(Resource.Error<TrackDomain>(e.localizedMessage ?: "An unexpected error occured"))
        }
        catch(e: IOException) {
            emit(Resource.Error<TrackDomain>(e.localizedMessage ?: "An unexpected error occured"))
        }

    }
}