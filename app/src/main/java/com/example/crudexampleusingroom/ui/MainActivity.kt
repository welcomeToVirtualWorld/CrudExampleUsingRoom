package com.example.crudexampleusingroom.ui

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudexampleusingroom.Presenter
import com.example.crudexampleusingroom.R
import com.example.crudexampleusingroom.adapter.MyAdapter
import com.example.crudexampleusingroom.databinding.ActivityMainBinding
import com.example.crudexampleusingroom.db.SubscriberDao
import com.example.crudexampleusingroom.db.Subscribers
import com.example.crudexampleusingroom.db.SubscribersDatabase
import com.example.crudexampleusingroom.repository.SubscriberRepository
import com.example.crudexampleusingroom.viewmodel.SubscriberViewModel
import com.example.crudexampleusingroom.viewmodel.SubscriberViewModelFactory

class MainActivity : AppCompatActivity(), MyAdapter.ClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao: SubscriberDao = SubscribersDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.lifecycleOwner = this
        binding.myViewModel = subscriberViewModel
        initRecyclerView()
        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displaySubscriberList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i("MyTag",it.toString())
            binding.rvSubscribers.adapter = MyAdapter(it,this)
        })
    }
    private fun initRecyclerView(){
        binding.rvSubscribers.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }

    override fun onRowClick(subscribers: Subscribers) {
       subscriberViewModel.updateOrDelete(subscribers)
    }


}