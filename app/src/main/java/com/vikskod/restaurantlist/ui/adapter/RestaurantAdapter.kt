package com.vikskod.restaurantlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.like.LikeButton
import com.like.OnLikeListener
import com.vikskod.restaurantlist.R
import com.vikskod.restaurantlist.data.model.FoodHitX
import com.vikskod.restaurantlist.databinding.RvListRestaurantBinding

class RestaurantAdapter() : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    // Helper for computing the difference between two lists
    private val callback = object : DiffUtil.ItemCallback<FoodHitX>() {
        override fun areItemsTheSame(oldItem: FoodHitX, newItem: FoodHitX): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: FoodHitX, newItem: FoodHitX): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RvListRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = differ.currentList[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RestaurantViewHolder(private val binding: RvListRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(HitX: FoodHitX) {
            binding.tvTitle.text = HitX.fields.item_name
            binding.tvAddress.text = "HitX.location.address"
            binding.btnLike.isLiked =true// HitX.isFavourite
            binding.btnLike.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton) {
                 //   HitX.isFavourite = true
                    onItemClickListener?.let {
                        it(HitX, true)
                    }
                }

                override fun unLiked(likeButton: LikeButton) {
                //    HitX.isFavourite = false
                    onItemClickListener?.let {
                        it(HitX, false)
                    }
                }
            })

            Glide.with(binding.ivBanner.context)
                .load(HitX.fields)
                .placeholder(R.drawable.ic_restaurant)
                .into(binding.ivBanner)


            binding.rlContainer.setOnClickListener {
                onContainerClickListener?.let {
                    it(HitX)
                }
            }
        }
    }

    private var onItemClickListener: ((FoodHitX, Boolean) -> Unit)? = null
    fun setOnItemClickListener(listener: (FoodHitX, Boolean) -> Unit) {
        onItemClickListener = listener
    }

    private var onContainerClickListener: ((FoodHitX) -> Unit)? = null
    fun setOnContainerClickListener(listener: (FoodHitX) -> Unit) {
        onContainerClickListener = listener
    }
}