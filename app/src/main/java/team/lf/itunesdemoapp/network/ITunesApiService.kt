package team.lf.itunesdemoapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://itunes.apple.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ITunesApiService {
    @GET("search")
    fun getCollectionsByCollectionNameAsync(
        @Query("term") term: String,
        @Query("entity") entity: String = "album",
        @Query("attribute") attribute: String = "albumTerm"
    ) : Deferred<NetworkContainer>

    @GET("lookup")
    fun getTracksByCollectionIdAsync(
        @Query("id") id: String,
        @Query("entity") entity: String = "song"
    ): Deferred<NetworkContainer>
}

object ITunesApi{
    val retrofitService: ITunesApiService by lazy { retrofit.create(ITunesApiService::class.java) }
}


//    https://itunes.apple.com/lookup?entity=album&id=214403406
//    https://itunes.apple.com/lookup?entity=song&id=1459409129
//    https://itunes.apple.com/search?media=album&entity=album&term=Between
//    https://itunes.apple.com/lookup?entity=song&id=214403406

// https://itunes.apple.com/search?term=between&entity=album - ищет по term альбомы,
// у которых либо автор, либо название cодержит term

//https://itunes.apple.com/search?term=between&entity=album&attribute=albumTerm ищет по term альбомы,
//// у которых  либо название cодержит term