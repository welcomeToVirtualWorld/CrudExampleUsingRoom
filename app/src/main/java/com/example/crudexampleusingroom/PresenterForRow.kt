package com.example.crudexampleusingroom

import com.example.crudexampleusingroom.db.Subscribers

interface PresenterForRow {
    fun onItemClick(subscriber:Subscribers)
}