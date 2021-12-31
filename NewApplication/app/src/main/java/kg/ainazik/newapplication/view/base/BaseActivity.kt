package kg.ainazik.newapplication.view.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kg.ainazik.newapplication.R
import kg.ainazik.newapplication.model.navigation.FragmentTransaction

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var navigation: NavController

    protected fun initializeNavController() {
        navigation = Navigation.findNavController(this, R.id.fragmentContainerView)
    }

    fun navigateToFragment(fragmentTransaction: FragmentTransaction) {
        navigation.navigate(
            fragmentTransaction.navigationId,
            fragmentTransaction.bundle,
            fragmentTransaction.navOptions
        )
    }

    fun popBackStack(@IdRes navigationId: Int, inclusive: Boolean = false) {
        navigation.popBackStack(navigationId, inclusive)
    }
}