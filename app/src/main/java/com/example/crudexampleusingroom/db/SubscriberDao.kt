package com.example.crudexampleusingroom.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subscriber: Subscribers):Long

    @Update
    suspend fun update(sub:Subscribers)

    @Query("SELECT * FROM subscribers")
    fun getAllSubscribers():LiveData<List<Subscribers>>

    @Delete
    suspend fun delete(sub:Subscribers)

    @Query("DELETE FROM subscribers")
    suspend fun deleteAll()
}