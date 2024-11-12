package com.dza.retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dza.retrofit.databinding.ActivityMainBinding
import com.dza.retrofit.model.Users
import com.dza.retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set LinearLayoutManager for RecyclerView
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        // Fetch data from API
        ApiClient.getInstance().getAllUsers().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    val usersList = response.body()?.data ?: listOf()

                    // Set adapter with list and click listener
                    binding.rvUser.adapter = UserAdapter(usersList) { user ->
                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("userId", user.id)
                        intent.putExtra("userName", "${user.firstName} ${user.lastName}")
                        intent.putExtra("userEmail", user.email)
                        intent.putExtra("userAvatar", user.avatar)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal memuat data", Toast.LENGTH_LONG).show()
                    Log.e("MainActivity", "Response error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error", Toast.LENGTH_LONG).show()
            }
        })
    }
}