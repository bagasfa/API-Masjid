package com.example.fanexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_slideshow.*
import org.json.JSONObject

class SlideshowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        val recyclerViewSlide = findViewById(R.id.recyclerViewSlide) as RecyclerView
        recyclerViewSlide.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val slides = ArrayList<NewSlide>()

        btnBackSlide.setOnClickListener{
            val inten = Intent(this, MenuActivity::class.java)
            startActivity(inten)
        }

        AndroidNetworking.get("http://$ip/jamSholat/slideshow-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResp", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))
                        Log.e("_kotlinTitle", jsonObject.optString("url_slideshow"))

                        var judul = jsonObject.optString("judul_slideshow")
                        var url = jsonObject.optString("url_slideshow")

                        slides.add(NewSlide("$judul", "$url"))
                    }

                    val adapter = AdapterSlide(slides)
                    recyclerViewSlide.adapter = adapter
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }

}
