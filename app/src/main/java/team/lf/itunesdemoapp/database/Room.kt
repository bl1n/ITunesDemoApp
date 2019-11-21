package team.lf.itunesdemoapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ITunesDemoDao {
    @Query("select * from DBSearchResult")
    fun getSearchResults(): LiveData<List<DBSearchResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(results: List<DBSearchResult>)

    @Query("DELETE FROM DBSearchResult")
    fun clear()


    //collections
    @Query("select * from collections where collectionName = :collectionName")
    fun getCollectionsByCollectionName(collectionName:String): LiveData<List<LookupEntity.Collection>>

    @Query("select * from collections where id = :collectionId")
    fun getCollectionsByCollectionsId(collectionId:String): LiveData<List<LookupEntity.Collection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollections(collections: List<LookupEntity.Collection>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: LookupEntity.Collection)

    //tracks
    @Query("select COUNT(id) from tracks where collectionId = :collectionId")
    fun getTracksCountByCollectionId(collectionId:String): Int

    @Query("select * from tracks where collectionId = :collectionId")
    fun getTracksByCollectionsId(collectionId:String): LiveData<List<LookupEntity.Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracks(tracks: List<LookupEntity.Track>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: LookupEntity.Track)

    @Update
    fun updateTrack(track:LookupEntity.Track)

    @Query("select * from tracks where id =:id")
    fun getTrack(id:String):LookupEntity.Track

    //artists
    @Query("select * from artists where artistName = :artistName")
    fun getArtistByArtistName(artistName:String): LiveData<List<LookupEntity.Artist>>

    @Query("select * from artists where id = :artistId")
    fun getArtistByArtistId(artistId:String): LiveData<List<LookupEntity.Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: LookupEntity.Artist)

}

@Database(entities = [DBSearchResult::class, LookupEntity.Collection::class, LookupEntity.Artist::class, LookupEntity.Track::class], version = 1)
abstract class ITunesDemoDatabase: RoomDatabase(){
    abstract val iTunesDemoDao: ITunesDemoDao
}

private lateinit var INSTANCE : ITunesDemoDatabase

fun getDatabase(context: Context): ITunesDemoDatabase{
    synchronized(ITunesDemoDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ITunesDemoDatabase::class.java,
                "searches").build()
        }
    }
    return INSTANCE
}
