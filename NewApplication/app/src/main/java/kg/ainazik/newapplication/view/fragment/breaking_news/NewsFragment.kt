package kg.ainazik.newapplication.view.fragment.breaking_news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kg.ainazik.newapplication.adapter.NewsAdapter
import kg.ainazik.newapplication.databinding.BreakingNewsLayoutBinding
import kg.ainazik.newapplication.view.base.BaseFragment
import kg.ainazik.newapplication.view.base.BaseViewModel

@AndroidEntryPoint
class NewsFragment : BaseFragment() {

    private var binding: BreakingNewsLayoutBinding? = null
    private val newsAdapter = NewsAdapter()
    private val viewModel: NewsViewModel by viewModels()

    override fun provideViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BreakingNewsLayoutBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLiveData()
        setupOnClickListener()
        setupEditText()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupLiveData() {
        viewModel.breakingNews.observe(viewLifecycleOwner) {
            newsAdapter.addAllItem(it.articles?.toMutableList())
        }

        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it.isVisible
        }
    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupOnClickListener() {
        binding?.refresh?.setOnClickListener {
            viewModel.getBreakingNews()
            binding?.progressBar?.isVisible = true
        }

        newsAdapter.setOnItemClickListener {
            viewModel.onArticleClick(it)
        }

        binding?.savedNews?.setOnClickListener {
            viewModel.onSavedNewsClick()
        }
    }

    private fun setupEditText() {
        binding?.searchEditText?.setOnEditorActionListener { text, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding?.searchEditText?.clearFocus()
                viewModel.searchNews(text.text.toString())
                val input: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                input.hideSoftInputFromWindow(binding?.searchEditText?.windowToken, 0)
            }
            true
        }
    }
}