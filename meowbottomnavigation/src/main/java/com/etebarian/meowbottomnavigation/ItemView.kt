package com.etebarian.meowbottomnavigation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Icon
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.item_cell_view.view.*

class ItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cell_view,this,false)
        // init ConstraintLayout custom view
        val set = ConstraintSet()
        addView(view)
        set.clone(this)
        set.applyTo(this)
        ic_badge.isInvisible = true
    }

    fun setResource(icon: Int) {
        btn_item.setImageResource(icon)
    }

    fun showBadgeDrawable() {
        ic_badge.isInvisible = false
    }

}