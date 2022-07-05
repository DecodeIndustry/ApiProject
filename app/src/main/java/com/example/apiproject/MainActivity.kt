package com.example.apiproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.apiproject.databinding.ActivityMainBinding
import com.example.apiproject.models.HadisItemModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getHadis()

        binding.buttonVolly.setOnClickListener {

            startActivity(Intent(this@MainActivity,VollyActivity::class.java))

        }


    }

    private fun getHadis() {

        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://quraninhindi.muhammadigyaan.com/").build().create(ApiInterface::class.java)

        val retroData = retrofitBuilder.getHadis()
        retroData.enqueue(object : Callback<List<HadisItemModel>?> {
            override fun onResponse(
                call: Call<List<HadisItemModel>?>,
                response: Response<List<HadisItemModel>?>
            ) {

                val stringBuilder = StringBuilder()
                val responseBdy = response.body()!!
                for (ds in responseBdy){

                    stringBuilder.append(ds.title)
                    stringBuilder.append("\n")

                }

                binding.textVVVV.text = stringBuilder

            }

            override fun onFailure(call: Call<List<HadisItemModel>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "onFailure: " +t.message)
            }
        })

    }
}