package ovh.alexisdelhaie.tpandroid.retrofit

import ovh.alexisdelhaie.tpandroid.pojos.People
import retrofit2.http.GET
import retrofit2.http.Headers

interface PeopleEndpoint {

    @GET("api")
    @Headers("Accept: application/json")
    suspend fun getRandomPeople(): People

}