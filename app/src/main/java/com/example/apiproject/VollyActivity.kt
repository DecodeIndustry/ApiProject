package com.example.apiproject

import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.apiproject.adapters.HadisAdapter

import com.example.apiproject.databinding.ActivityVollyBinding
import com.example.apiproject.models.HadisItemModel
import org.json.JSONException


class VollyActivity : AppCompatActivity() {

    lateinit var binding:ActivityVollyBinding
    private var requestQueue: RequestQueue? = null
    val URL = "https://quraninhindi.muhammadigyaan.com/get_hadies.php"
    lateinit var adapter:HadisAdapter
    lateinit var list:ArrayList<HadisItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVollyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)


        list = ArrayList()
        binding.vollyRecy.layoutManager = LinearLayoutManager(this)
        binding.vollyRecy.setHasFixedSize(true)

        apiCalls()

    }

    fun apiCalls(){

        val url = "https://quraninhindi.muhammadigyaan.com/get_hadies.php"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                response ->try {
            val jsonArray = response.getJSONArray("data")
            for (i in 0 until jsonArray.length()) {
                val data = jsonArray.getJSONObject(i)
                val id = data.getString("id")
                val lang = data.getString("language")
                val titles = data.getString("title")
                val details = data.getString("detail")
                val dates = data.getString("date")
                val times = data.getString("time")

                list.add(HadisItemModel(dates,details,id,lang,times,titles))

            }
            adapter = HadisAdapter(this@VollyActivity,list)
            binding.vollyRecy.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        }, {
                error -> error.printStackTrace()
            binding.progressBar.visibility = View.GONE
        })


        requestQueue?.add(request)
    }




}