package kg.ainazik.newapplication.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kg.ainazik.newapplication.R
import kg.ainazik.newapplication.model.navigation.FragmentTransaction
import kg.ainazik.newapplication.model.toast.ToastDuration
import kg.ainazik.newapplication.view.MainActivity

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoadingViewModel()
        provideViewModel()?.let { observableViewModel ->
            lifecycle.addObserver(observableViewModel)
        }
    }

    protected fun navigateToFragment(fragmentTransaction: FragmentTransaction) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).navigateToFragment(fragmentTransaction)
        }
    }

    protected fun showToast(message: String, duration: ToastDuration) {
        Toast.makeText(requireContext(), message, duration.value).show()
    }

    protected fun popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).popBackStack(destinationId, inclusive)
        }
    }

    private fun setupLoadingViewModel() {
        provideViewModel()?.let { observableViewModel ->

            observableViewModel.setToast.observe(this) { messageAndDuration ->
                showToast(
                    message = messageAndDuration.first,
                    duration = messageAndDuration.second
                )
            }

            observableViewModel.navigateToFragment.observe(this) { transaction ->
                navigateToFragment(transaction)
            }

            observableViewModel.context = {
                requireContext()
            }

            observableViewModel.requiredArguments = {
                requireArguments()
            }

        }
    }

    abstract fun provideViewModel(): BaseViewModel?
}