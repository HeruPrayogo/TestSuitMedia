package com.test.Suitmedia.network

import com.test.Suitmedia.model.Data
import com.test.Suitmedia.model.SelectedUser
import com.test.Suitmedia.model.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestfulApi {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") per_page:Int
    ): Call<UserData>
    @GET("api/users/{id}?")
    fun getSelectedUsers(
        @Path("id") id: Int
    ): Call<SelectedUser>
}