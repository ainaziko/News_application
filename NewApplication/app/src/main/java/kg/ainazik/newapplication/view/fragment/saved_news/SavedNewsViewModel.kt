package kg.ainazik.newapplication.view.fragment.saved_news

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.ainazik.domain.interactor.usecase.news.DeleteArticle
import kg.ainazik.domain.interactor.usecase.news.GetArticles
import kg.ainazik.domain.model.news.ArticleModel
import kg.ainazik.newapplication.R
import kg.ainazik.newapplication.model.toast.ToastDuration
import kg.ainazik.newapplication.view.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val deleteArticle: DeleteArticle,
    private val getArticles: GetArticles
) : BaseViewModel() {

    private val _article = MutableLiveData<Array<ArticleModel>>()
    val article: LiveData<Array<ArticleModel>> get() = _article

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getArticles()
    }

    fun onArticleClick(model: ArticleModel) {
        val bundle = Bundle().apply {
            putSerializable("news", model)
        }
        navigateFragment(R.id.action_savedNewsFragment_to_articleFragment, bundle)
    }

    private fun getArticles() {
        showLoading()
        getArticles.execute(viewModelScope) {
            hideLoading()
            handleResult(it) { result ->
                _article.postValue(result)
            }
        }
    }

    fun deleteArticle(articleModel: ArticleModel?, onSuccess: () -> Unit) {
        deleteArticle.execute(viewModelScope, articleModel) {
            it.perform({
                showToast("Новость удалена", ToastDuration.SHORT)
                onSuccess.invoke()
            }, {
                showToast("Ошибка, повторите попытку!", ToastDuration.SHORT)
            })
        }
    }
}