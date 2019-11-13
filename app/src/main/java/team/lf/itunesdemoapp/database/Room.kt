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
}

@Database(entities = [DBSearchResult::class], version = 1)
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
