package com.example.crudexampleusingroom.repository

import com.example.crudexampleusingroom.db.SubscriberDao
import com.example.crudexampleusingroom.db.Subscribers

class SubscriberRepository(val dao:SubscriberDao) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(sub:Subscribers){
        dao.insert(sub)
    }

    suspend fun update(sub:Subscribers){
        dao.update(sub)
    }

    suspend fun delete(sub: Subscribers){
        dao.delete(sub)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }
}