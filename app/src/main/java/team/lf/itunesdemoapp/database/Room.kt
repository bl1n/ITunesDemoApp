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
    fun getCollectionsByCollectionName(collectionName:String): LiveData<List<DatabaseModel.Collection>>

    @Query("select * from collections where id = :collectionId")
    fun getCollectionsByCollectionsId(collectionId:String): LiveData<List<DatabaseModel.Collection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollections(collections: List<DatabaseModel.Collection>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: DatabaseModel.Collection)

    //tracks
    @Query("select * from tracks where trackName = :trackName")
    fun getTracksByTrackName(trackName:String): LiveData<List<DatabaseModel.Track>>

    @Query("select * from tracks where collectionId = :collectionId")
    fun getTracksByCollectionsId(collectionId:String): LiveData<List<DatabaseModel.Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracks(tracks: List<DatabaseModel.Track>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrack(track: DatabaseModel.Track)

    //artists
    @Query("select * from artists where artistName = :artistName")
    fun getArtistByArtistName(artistName:String): LiveData<List<DatabaseModel.Artist>>

    @Query("select * from artists where id = :artistId")
    fun getArtistByArtistId(artistId:String): LiveData<List<DatabaseModel.Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: DatabaseModel.Artist)

}

@Database(entities = [DBSearchResult::class, DatabaseModel.Collection::class, DatabaseModel.Artist::class, DatabaseModel.Track::class], version = 1)
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
