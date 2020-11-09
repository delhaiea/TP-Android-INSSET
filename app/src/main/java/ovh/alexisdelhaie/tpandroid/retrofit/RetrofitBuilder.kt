package ovh.alexisdelhaie.tpandroid.retrofit

import com.google.gson.GsonBuilder
import ovh.alexisdelhaie.tpandroid.pojos.People
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")

        .addConverterFactory(GsonConverterFactory
            .create(GsonBuilder().setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(People::class.java, ResultDeserializer<People>())
                .create()))
        .build()


    fun getPeople(): PeopleEndpoint = retrofit.create(PeopleEndpoint::class.java)

}