package com.example.crudexampleusingroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscribers::class], version = 2)
abstract class SubscribersDatabase : RoomDatabase() {

    abstract val subscriberDAO: SubscriberDao


    companion object {
        private var INSTANCE: SubscribersDatabase? = null
        fun getInstance(context: Context): SubscribersDatabase {
            synchronized(this) {
                var instance: SubscribersDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscribersDatabase::class.java,
                        "subscriber_data_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }

        }
    }
}