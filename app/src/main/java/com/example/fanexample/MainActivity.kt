package com.example.fanexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

const val ip = "192.168.1.5"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(getApplicationContext());

        val context = this

        back.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java)
            startActivity(intent)
        }

        getJadwal()

        update.setOnClickListener{
            var dataShubuh = txtSubuh.text.toString()
            var dataDhuhur = txtDhuhur.text.toString()
            var dataAshar = txtAshar.text.toString()
            var dataMaghrib = txtMaghrib.text.toString()
            var dataIsya = txtIsya.text.toString()
            var dataDhuha = txtDhuha.text.toString()

            postSholat(dataShubuh,dataDhuhur,dataAshar,dataMaghrib,dataIsya,dataDhuha)
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun getJadwal(){
        AndroidNetworking.get("http://$ip/jamSholat/jadwal-json.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject) {
                        Log.e("_kotlinResponse", response.toString())

                        val jsonArray = response.getJSONArray("result")
                        for (i in 0 until jsonArray.length()){
                            val jsonObject = jsonArray.getJSONObject(i)
                            Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                            subuh.setText(jsonObject.optString("shubuh"))
                            dhuha.setText(jsonObject.optString("dhuha"))
                            dhuhur.setText(jsonObject.optString("dhuhur"))
                            ashar.setText(jsonObject.optString("ashar"))
                            maghrib.setText(jsonObject.optString("maghrib"))
                            isya.setText(jsonObject.optString("isha"))
                        }
                    }

                    override fun onError(anError: ANError) {
                        Log.i("_err", anError.toString())
                    }
                })
    }

    fun postSholat(dataSubuh:String, dataDhuhur:String, dataAshar:String, dataMaghrib:String, dataIsya:String, dataDhuha:String){
        AndroidNetworking.post("http://$ip/jamSholat/update_sholat-json.php")
            .addBodyParameter("shubuh", dataSubuh)
            .addBodyParameter("dhuhur", dataDhuhur)
            .addBodyParameter("ashar", dataAshar)
            .addBodyParameter("maghrib", dataMaghrib)
            .addBodyParameter("isha", dataIsya)
            .addBodyParameter("dhuha", dataDhuha)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {}
                override fun onError(anError: ANError?) {}

            })

    }
}
