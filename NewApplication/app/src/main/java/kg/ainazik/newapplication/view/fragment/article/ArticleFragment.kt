package kg.ainazik.newapplication.view.fragment.article

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kg.ainazik.newapplication.databinding.ArticleLayoutBinding
import kg.ainazik.newapplication.view.base.BaseFragment
import kg.ainazik.newapplication.view.base.BaseViewModel

@AndroidEntryPoint
class ArticleFragment : BaseFragment() {

    private var binding: ArticleLayoutBinding? = null
    private val viewModel: ArticleViewModel by viewModels()

    override fun provideViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArticleLayoutBinding.inflate(LayoutInflater.from(context), container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
        setupOnClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(url: String?) {
        binding?.webView?.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("$url")
        }
    }

    private fun setupLiveData() {
        viewModel.article.observe(viewLifecycleOwner) {
            setupWebView(it.url)
        }
    }

    private fun setupOnClickListener() {
        binding?.saveArticle?.setOnClickListener {
            viewModel.onSaveArticleClick()
        }
    }
}