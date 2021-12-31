package kg.ainazik.newapplication.view.fragment.breaking_news

import android.os.Bundle
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.ainazik.domain.interactor.usecase.news.GetBreakingNews
import kg.ainazik.domain.interactor.usecase.news.SearchNews
import kg.ainazik.domain.model.news.ArticleModel
import kg.ainazik.domain.model.news.NewsModel
import kg.ainazik.newapplication.R
import kg.ainazik.newapplication.view.base.BaseActivity
import kg.ainazik.newapplication.view.base.BaseViewModel
import kg.ainazik.newapplication.view.fragment.article.ArticleFragmentArgs
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getBreakingNews: GetBreakingNews,
    private val searchNews: SearchNews
) : BaseViewModel() {

    private val _breakingNews = MutableLiveData<NewsModel>()
    val breakingNews: LiveData<NewsModel> get() = _breakingNews

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getBreakingNews()
    }

    fun getBreakingNews() {
        showLoading()
        getBreakingNews.execute(viewModelScope) {
            handleResult(it) { result ->
                hideLoading()
                _breakingNews.postValue(result)
            }
        }
    }

    fun searchNews(searchQuery: String?) {
        if (searchQuery.isNullOrEmpty()) return
        showLoading()
        searchNews.execute(viewModelScope, searchQuery) {
            hideLoading()
            handleResult(it) { result ->
                _breakingNews.postValue(result)
            }
        }
    }

    fun onArticleClick(model: ArticleModel) {
        val bundle = Bundle().apply {
            putSerializable("news", model)
        }
        navigateFragment(R.id.action_newsFragment_to_articleFragment, bundle)
    }

    fun onSavedNewsClick() {
        navigateFragment(R.id.action_newsFragment_to_savedNewsFragment)
    }
}