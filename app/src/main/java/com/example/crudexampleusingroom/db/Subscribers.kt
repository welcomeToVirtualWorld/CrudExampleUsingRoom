package com.example.crudexampleusingroom.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="subscribers")
class Subscribers(
    @PrimaryKey(autoGenerate = true)
    val subscriber_id:Long,
    val subscriber_name:String,
    val subscriber_email:String
){
    constructor(subscriber_name:String,subscriber_email:String) : this(0,subscriber_name,subscriber_email)
}