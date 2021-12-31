package kg.ainazik.newapplication.model.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavOptions

data class FragmentTransaction(
    @IdRes val navigationId: Int,
    val bundle: Bundle? = null,
    val navOptions: NavOptions? = null
)