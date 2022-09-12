package com.heechan.class205manager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heechan.class205manager.model.Meal

class MealListAdapter(
    private val items: List<Meal>,
    private val year: Int,
    private val month: Int,
) : RecyclerView.Adapter<MealListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_meal_item, parent, false)

        return MealListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        holder.bind(items[position], year, month)
    }

    override fun getItemCount(): Int = items.size
}