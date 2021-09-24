package com.example.socioinfonavit.network

import android.database.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

object ApiService {

    private var instance : Retrofit? = null
    private val baseUrl = "https://staging.api.socioinfonavit.io/api/v1/"
    var getMethods : GetInterfaceMethods?
    var postMethods : PostInterfaceMethods?

    val AUTH_HEADER = "Authorization"
    var jwt = ""

    init {
        instance = Retrofit.Builder()
            .client(getClient())
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create()).build()

        getMethods = instance?.create(GetInterfaceMethods::class.java)
        postMethods =  instance?.create(PostInterfaceMethods::class.java)
    }

    fun destroyClient(){
        instance = null
        jwt = ""
    }

    private fun getClient() : OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(AUTH_HEADER, jwt)
                    .build()
                chain.proceed(newRequest)
            }

        return builder.build()
    }

    interface PostInterfaceMethods {
        @POST("login")
        fun  login(@Body credentials: UserRequest) : Call<UserResponse>

        @DELETE("logout")
        fun  logout(): Call<Unit>
    }

    interface GetInterfaceMethods {
        @GET("member/wallets")
        suspend fun getAllWallets() : List<Wallet>
        @GET("member/landing_benevits")
        suspend fun getAllBenevits() : BenevitsResponse
    }
}