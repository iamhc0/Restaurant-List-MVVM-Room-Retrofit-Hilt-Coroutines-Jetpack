package com.vikskod.restaurantlist.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vikskod.restaurantlist.R
import com.vikskod.restaurantlist.data.model.FoodHitX
import com.vikskod.restaurantlist.databinding.FragmentRestaurantBinding
import com.vikskod.restaurantlist.ui.adapter.RestaurantAdapter
import com.vikskod.restaurantlist.ui.viewmodel.RestaurantViewModel
import com.vikskod.restaurantlist.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AllRestaurantFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var viewModel: RestaurantViewModel
    private val TAG = AllRestaurantFragment::class.java.name

    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRestaurantBinding.bind(view)
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]

        initRecyclerView()
        setupObservers()
    }

    private fun initRecyclerView() {
        binding.rvRestaurant.apply {
            adapter = restaurantAdapter
            layoutManager = GridLayoutManager(activity,2)
        }

        restaurantAdapter.setOnItemClickListener { restaurant, isLiked ->
            viewModel.setFavouriteRestaurant(restaurant)
        }

        restaurantAdapter.setOnContainerClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_restaurant", it)
                putString(
                    "title",
                    it.fields.brand_name
                )      // passing title as an argument to set restaurant title on toolbar
            }
            findNavController().navigate(
                R.id.action_allRestaurantFragment_to_restaurantDetailFragment,
                bundle
            )
        }
    }

    private fun setupObservers() {
        showEmptyMessage(false)
        viewModel.getAllRestaurant.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {

                    showProgressBar(false)
                    if (!it.data.isNullOrEmpty()) {
                        // Doing this because Unwanted extra JsonObject is added on api response
                        val finalData = ArrayList<FoodHitX>()
                        for (item in it.data.toList())
                            finalData.add(item)

                        if (finalData.isEmpty()) {
                            showEmptyMessage(true)
                        }
                        Log.d(TAG, "setupObservers-> final Data: $finalData")
                        restaurantAdapter.differ.submitList(finalData)
                    }
                }
                Resource.Status.ERROR -> {
                    showEmptyMessage(true)
                    showProgressBar(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING ->
                    showProgressBar(true)
            }
        })
    }

    private fun showProgressBar(showProgress: Boolean) {
        if (showProgress)
            binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun showEmptyMessage(show: Boolean) {
        if (show) {
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.tvEmpty.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }


}