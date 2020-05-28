package com.etebarian.meowbottomnavigaion

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.etebarian.meowbottomnavigaion.databinding.ActivityMainBinding
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    companion object {
        /**
         * NavigationBar ID
         */
        private const val ID_HOME = 1
        private const val ID_EXPLORE = 2
        private const val ID_MESSAGE = 3
        private const val ID_NOTIFICATION = 4
        private const val ID_ACCOUNT = 5
        /**
         * subCells ID
         */
        private const val ID_SUB_HOME = 6
        private const val ID_SUB_EXPLORE = 7
        private const val ID_SUB_MESSAGE = 8
        private const val ID_SUB_NOTIFICATION = 9
    }

//    @SuppressLint("NewApi")
//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//    override fun attachBaseContext(newBase: Context?) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            super.attachBaseContext(MyContextWrapper.wrap(newBase, "fa"))
//        } else {
//            super.attachBaseContext(newBase)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val tvSelected = binding.tvSelected
        tvSelected.typeface = Typeface.createFromAsset(assets, "fonts/SourceSansPro-Regular.ttf")
        binding.bottomNavigation.apply {
            add(MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home,
                    arrayListOf(
                            MeowBottomNavigation.Model(ID_SUB_HOME, R.drawable.ic_home),
                            MeowBottomNavigation.Model(ID_SUB_EXPLORE, R.drawable.ic_explore),
                            MeowBottomNavigation.Model(ID_SUB_MESSAGE, R.drawable.ic_message),
                            MeowBottomNavigation.Model(ID_SUB_NOTIFICATION, R.drawable.ic_notification)
                    )))
            add(MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_explore))
            add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message))
            add(MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification))
            // navBar icon 加入 badgeNumber
            setCount(ID_HOME, "222")
            setCount(ID_NOTIFICATION, "")
            // subCells icon 加入 badgeNumber
            setSubItemBadgeDraw(ID_SUB_HOME, "222")
            setSubItemBadgeDraw(ID_SUB_MESSAGE, "")
            // 初始設定自動彈出第一個 NavBar icon
            show(ID_HOME)
            setOnShowListener {
                val name = when (it.id) {
                    ID_HOME -> "HOME"
                    ID_EXPLORE -> "EXPLORE"
                    ID_MESSAGE -> "MESSAGE"
                    ID_NOTIFICATION -> "NOTIFICATION"
                    ID_ACCOUNT -> "ACCOUNT"
                    else -> ""
                }
                tvSelected.text = getString(R.string.main_page_selected, name)
            }

            setOnClickMenuListener {
//                Log.d("msg", "onClickMenuListener")
                val name = when (it.id) {
                    ID_HOME -> "HOME"
                    ID_EXPLORE -> "EXPLORE"
                    ID_MESSAGE -> "MESSAGE"
                    ID_NOTIFICATION -> "NOTIFICATION"
                    ID_ACCOUNT -> "ACCOUNT"
                    else -> ""
                }
            }

            setOnReselectListener {
                Toast.makeText(context, "item ${it.id} is reselected.", Toast.LENGTH_LONG).show()
            }

            /**
             *  subItem clickListener
             */
            setCellOnClickListener(object : MeowBottomNavigation.CellOnClickListener {
                override fun cellOnClickListener(id: Int) {
                    Log.d("msg", "CellOnClickListener")
                    Log.d("msg", "id: $id")
                }
            })
        }
    }
}
