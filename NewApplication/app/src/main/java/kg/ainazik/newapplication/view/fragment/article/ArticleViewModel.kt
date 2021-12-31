package kg.ainazik.newapplication.view.fragment.article

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.ainazik.domain.interactor.usecase.news.SaveArticle
import kg.ainazik.domain.model.news.ArticleModel
import kg.ainazik.newapplication.model.toast.ToastDuration
import kg.ainazik.newapplication.view.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val saveArticle: SaveArticle
) : BaseViewModel() {

    private val _isSaved = MutableLiveData<Boolean>()
    val isSaved: LiveData<Boolean> get() = _isSaved

    private val _article = MutableLiveData<ArticleModel>()
    val article: LiveData<ArticleModel> get() = _article

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        val article = ArticleFragmentArgs.fromBundle(requiredArguments()).news
        _article.postValue(article)
        _isSaved.postValue(article?.isSaved)
    }

    fun onSaveArticleClick() {
        saveArticle.execute(viewModelScope, _article.value) {
            it.perform(
                {
                    showToast("Новость сохранена!", ToastDuration.SHORT)
                }, {
                    showToast("Ошибка, повторите попытку!", ToastDuration.SHORT)
                }
            )
        }
    }
}