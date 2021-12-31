package kg.ainazik.newapplication.view

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kg.ainazik.newapplication.R
import kg.ainazik.newapplication.view.base.BaseActivity

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        initializeNavController()
    }
}