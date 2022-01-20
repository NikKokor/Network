package com.example.network

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit =  Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkService: Network = retrofit.create(Network::class.java)
        networkService.getUser("NikKokor").enqueue(object: Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {}

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val userInfo: UserInfo? = response.body()

                val userAvatar: ImageView = findViewById(R.id.Avatar)
                val userLogin: TextView = findViewById(R.id.Login)
                val publicRepos: TextView = findViewById(R.id.Repos)

                Glide.with(applicationContext).load(userInfo?.avatarUrl).into(userAvatar)
                userLogin.text = userInfo?.login
                publicRepos.text = "Открытых репозиториев: " + userInfo?.publicRepos.toString()

                userLogin.setOnClickListener(View.OnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(userInfo?.url))
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    applicationContext.startActivity(intent)
                })
            }
        })
    }
}
