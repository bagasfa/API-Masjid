package com.example.fanexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONArray
import org.json.JSONObject

class PengumumanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)
        swAktif.setChecked(true)

        // Set an checked change listener for switch button
        swAktif.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                txt_Sw.text = "Aktif"
            } else {
                // The switch is disabled
                txt_Sw.text = "Nonaktif"
            }
        }

        val context = this

        back.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java)
            startActivity(intent)
        }

        getPengumuman()

        update.setOnClickListener{
            var dataJudul = txtJudul.text.toString()
            var dataIsi = txtIsi.text.toString()
            var dataStatus = txt_Sw.text.toString()

            postPengumuman(dataJudul, dataIsi, dataStatus)
            val intent = Intent(context,PengumumanActivity::class.java)
            startActivity(intent)
        }
    }

    fun getPengumuman(){
        AndroidNetworking.get("http://$ip/jamSholat/pengumuman-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_pengumuman"))

                        judul.setText(jsonObject.optString("judul_pengumuman"))
                        isi.setText(jsonObject.optString("isi_pengumuman"))
                        aktif.setText(jsonObject.optString("aktif"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postPengumuman(dataJudul:String, dataIsi:String, dataStatus:String){
        AndroidNetworking.post("http://$ip/jamSholat/update_pengumuman-json.php")
            .addBodyParameter("judul_pengumuman", dataJudul)
            .addBodyParameter("isi_pengumuman", dataIsi)
            .addBodyParameter("aktif", dataStatus)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {}
                override fun onError(anError: ANError?) {}

            })
    }
}
