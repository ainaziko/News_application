package kg.ainazik.newapplication.view.fragment.saved_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kg.ainazik.newapplication.adapter.NewsAdapter
import kg.ainazik.newapplication.databinding.SavedNewsLayoutBinding
import kg.ainazik.newapplication.view.base.BaseFragment
import kg.ainazik.newapplication.view.base.BaseViewModel

@AndroidEntryPoint
class SavedNewsFragment : BaseFragment() {

    private var binding: SavedNewsLayoutBinding? = null
    private val viewModel: SavedNewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    override fun provideViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SavedNewsLayoutBinding.inflate(LayoutInflater.from(context), container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLiveData()
        setupClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupLiveData() {
        viewModel.article.observe(viewLifecycleOwner) {
            newsAdapter.addAllItem(it.toMutableList())
        }

        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it.isVisible
        }
    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())


            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val article = newsAdapter.getItem(position)
                    viewModel.deleteArticle(article) {
                        newsAdapter.removeItem(position)
                    }
                }
            }

            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(this)

        }
    }

    private fun setupClickListener() {
        newsAdapter.setOnItemClickListener {
            viewModel.onArticleClick(it)
        }
    }

}