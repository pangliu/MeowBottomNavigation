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
        private const val ID_HOME = 1
        private const val ID_EXPLORE = 2
        private const val ID_MESSAGE = 3
        private const val ID_NOTIFICATION = 4
        private const val ID_ACCOUNT = 5
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
                            MeowBottomNavigation.Model(11, R.drawable.ic_account),
                            MeowBottomNavigation.Model(22, R.drawable.ic_explore),
                            MeowBottomNavigation.Model(33, R.drawable.ic_home),
                            MeowBottomNavigation.Model(44, R.drawable.ic_home)
                    )))
            add(MeowBottomNavigation.Model(ID_EXPLORE, R.drawable.ic_explore))
            add(MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message))
            add(MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notification))
            add(MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account,
                    arrayListOf(
                            MeowBottomNavigation.Model(1,R.drawable.ic_home),
                            MeowBottomNavigation.Model(2,R.drawable.ic_home),
                            MeowBottomNavigation.Model(3,R.drawable.ic_home),
                            MeowBottomNavigation.Model(4,R.drawable.ic_home),
                            MeowBottomNavigation.Model(5,R.drawable.ic_home),
                            MeowBottomNavigation.Model(6,R.drawable.ic_home),
                            MeowBottomNavigation.Model(7,R.drawable.ic_home))))

            setCount(ID_ACCOUNT, "")
//            show(ID_ACCOUNT)
            setSubItemBadgeDraw(ID_HOME, 22)
            show(ID_HOME)
            setOnShowListener {
                Log.d("msg", "onShowListener")
                val name = when (it.id) {
                    ID_HOME -> "HOME"
                    ID_EXPLORE -> "EXPLORE"
                    ID_MESSAGE -> "MESSAGE"
                    ID_NOTIFICATION -> "NOTIFICATION"
                    ID_ACCOUNT -> "ACCOUNT"
                    else -> ""
                }
                tvSelected.text = getString(R.string.main_page_selected, name)
//                Log.d("msg", "model name: " + name)
//                Log.d("msg", "model size: " + it.subList.size)

            }

            setOnClickMenuListener {
                Log.d("msg", "onClickMenuListener")
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
                Log.d("msg", "OnReselectListener")
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
