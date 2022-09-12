package com.heechan.class205manager

import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.heechan.class205manager.model.Meal

class MealListViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
    private val date = row.findViewById<TextView>(R.id.txt_mealItem_date)
    private val menu = row.findViewById<TextView>(R.id.txt_mealItem_men)

    private val vibrator = row.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    init {
        row.setOnLongClickListener {
            vibrator.vibrate(100)

            val message = "오늘 급식\n\n${menu.text}"

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
            }

            val shareIntent = Intent.createChooser(intent, "오늘 급식 공유")
            row.context.startActivity(shareIntent)

            true
        }
    }


    fun bind(mealData: Meal, year : Int, month : Int) {
        date.text = "${year}년 ${month}월 ${mealData.date}일"
        menu.text = if(mealData.menu.isEmpty()){
            "급식이 없어요"
        } else{
            val menuContent = StringBuffer()

            mealData.menu.forEachIndexed {index, value ->
                menuContent.append(value.split(".")[0])

                if(index != mealData.menu.size - 1)
                    menuContent.append("\n")
            }

            menuContent
        }
    }
}