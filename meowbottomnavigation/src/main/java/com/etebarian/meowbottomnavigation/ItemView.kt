package com.etebarian.meowbottomnavigation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Icon
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.item_cell_view.view.*

class ItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        const val EMPTY_VALUE = "empty"
    }

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

    fun showBadgeDrawable(count: String) {
        ic_badge.isInvisible = false
        this.count = count
    }

    var count: String? = EMPTY_VALUE
        set(value) {
            field = value
//            if (allowDraw) {
                if (count != null && count == EMPTY_VALUE) {
                    ic_badge.text = ""
                    ic_badge.visibility = View.INVISIBLE
                } else {
                    if (count != null && count?.length ?: 0 >= 3) {
                        field = count?.substring(0, 1) + ".."
                    }
                    ic_badge.text = count
                    ic_badge.visibility = View.VISIBLE
                    val scale = if (count?.isEmpty() == true) 0.5f else 1f
                    ic_badge.scaleX = scale
                    ic_badge.scaleY = scale
                }
//            }
        }

}