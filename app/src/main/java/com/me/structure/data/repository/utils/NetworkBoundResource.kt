package com.me.structure.data.repository.utils

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.me.structure.domain.features.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value =
                Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {

            try {
                fetchFromNetwork()
            } catch (e: Exception) {
                setValue(Resource.error(e, null))
            }

        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>


    private suspend fun fetchFromNetwork() {
        val apiResponse = createCallAsync().await()
        val result = processResponse(apiResponse)
        saveCallResults(result)
        setValue(Resource.success(result))
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType?

    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>
}