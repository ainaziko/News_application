package kg.ainazik.newapplication.model.loading

import androidx.annotation.StringRes
import kg.ainazik.newapplication.R

data class LoadingViewParams(
    val isVisible: Boolean,
    @StringRes val text: Int = R.string.progress_bar_status
)