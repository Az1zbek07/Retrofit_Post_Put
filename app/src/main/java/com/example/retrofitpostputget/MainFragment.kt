package com.example.retrofitpostputget

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpostputget.adapter.FoodAdapter
import com.example.retrofitpostputget.databinding.FragmentMainBinding
import com.example.retrofitpostputget.model.FoodResponse
import com.example.retrofitpostputget.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val foodAdapter by lazy { FoodAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        RetroInstance.apiService().getFoods().enqueue(object : Callback<FoodResponse>{
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                    binding.rv.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = foodAdapter
                        Log.d("@@@", "onResponse: ${response.code()}")
                }
                foodAdapter.submitList(response.body()?.foodList)
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.d("@@@", "onFailure: $t")
            }
        })

        binding.btnSearch.setOnClickListener {
            searchFood()
        }
    }

    private fun searchFood(){
        val food = binding.editSearch.text.toString().trim()
        RetroInstance.apiService().searchFood("", food).enqueue(object : Callback<FoodResponse>{
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                    binding.rv.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = foodAdapter
                    foodAdapter.submitList(response.body()?.foodList)
                }
            }

            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.d("@@@", "onFailure: $t")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}