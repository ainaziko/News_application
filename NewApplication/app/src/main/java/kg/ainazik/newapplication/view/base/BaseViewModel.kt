package kg.ainazik.newapplication.view.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import kg.ainazik.domain.interactor.result.Result
import kg.ainazik.newapplication.R
import kg.ainazik.newapplication.exeption.factory.ErrorMessageFactory
import kg.ainazik.newapplication.model.loading.LoadingParams
import kg.ainazik.newapplication.model.loading.LoadingViewParams
import kg.ainazik.newapplication.model.navigation.FragmentTransaction
import kg.ainazik.newapplication.model.toast.ToastDuration

abstract class BaseViewModel : LifecycleObserverViewModel() {

    lateinit var context: () -> Context
    lateinit var requiredArguments: () -> Bundle

    val navigateToFragment = SingleLiveEvent<FragmentTransaction>()
    val showLoading = SingleLiveEvent<LoadingParams>()
    val showLoadingView = SingleLiveEvent<LoadingViewParams>()
    val setToast = SingleLiveEvent<Pair<String, ToastDuration>>()

    fun showToast(message: String, duration: ToastDuration) {
        setToast.startEvent(Pair(message, duration))
    }

    fun navigateFragment(@IdRes navigationId: Int, bundle: Bundle? = null) {
        val fragmentTransaction = FragmentTransaction(navigationId, bundle)
        navigateToFragment.startEvent(fragmentTransaction)
    }

    fun showLoading() {
        showLoading.startEvent(LoadingParams(true))
    }

    fun hideLoading() {
        showLoading.startEvent(LoadingParams(false))
    }

    fun showLoadingView(@StringRes message: Int = R.string.progress_bar_status) {
        showLoadingView.startEvent(LoadingViewParams(true, message))
    }

    fun hideLoadingView() {
        showLoadingView.startEvent(LoadingViewParams(false))
    }

    fun <T> handleResult(result: Result<T>, onSuccess: (T) -> Unit) {
        result.perform(onSuccess = onSuccess, { throwable ->
            onCoroutinesFailed(throwable)
        })
    }

    protected fun getString(@StringRes resourceId: Int): String {
        return context().getString(resourceId)
    }

    private fun onCoroutinesFailed(
        throwable: Throwable,
        defaultErrorMessage: String = getString(R.string.userFriendly_errorMessage)
    ) {
        throwable.printStackTrace()

        val errorMessage = ErrorMessageFactory.create(throwable)
        showToast(errorMessage ?: defaultErrorMessage, ToastDuration.LONG)
        hideLoading()
    }
}
