package com.example.learnretrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.learnretrofit.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // this line creates an instance of an interface that is AlbumService using retrofit
        // basically it enables you to make request to the /albums that is our endpoint in our interface
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

        //this code defines a live data i.e responseLiveData that emits the response
        // from the retrofit service .getAlbums()
        // LiveData is typically used for observing and handling responses
        // then next we need to observe that data first
        val responseLiveData: LiveData<Response<Albums>> =
            liveData {
                val response = retrofitService.getAlbums()
                emit(response)
            }

        // this code observes the responseLiveData  and upon receiving a response
        // it iterates through the response body and store it in the albumList variable
        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            // if albumList is not null means it is present then use
            // while loop to iterate through each album and store it in albumItem variable and
            // for each album it extracts the title and store it in albumTitle variable and
            // finally append it to the titleTextView

            if (albumList != null){
                while (albumList.hasNext()){
                    val albumItem = albumList.next()

                    val albumTitle = "Album Title: ${albumItem.title} \n"
                    // we usually use .append() instead of .text() in webservices
                    binding.titleTextView.append(albumTitle)
                }
            }
        })

    }
}