package com.currencies.shared.support.parser

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONException
import retrofit2.HttpException
import java.io.EOFException
import javax.inject.Inject

class BackendErrorParser @Inject constructor() {

    private data class BackendError(

        /**
         * The name of the field with which the error is associated.
         */
        @Json(name = "detail")
        val detail: String?
    )

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun parseError(exception: HttpException): String? {
        val errorBody: String = exception.response()?.errorBody()?.string() ?: return null
        return try {
            val error = moshi.adapter(BackendError::class.java).fromJson(errorBody)
            error?.detail
        } catch (ex: JSONException) {
            null
        } catch (ex: EOFException) {
            val message = "page not found"
            message
        }
    }
}