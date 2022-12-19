package com.mertgolcu.wellbender.ui.explore

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentExploreBinding
import com.mertgolcu.wellbender.databinding.FragmentHistoryBinding
import com.mertgolcu.wellbender.ui.explore.adapter.BlogWriteAdapter
import com.mertgolcu.wellbender.ui.history.HistoryViewModel
import com.mertgolcu.wellbender.ui.main.MainFragment
import com.mertgolcu.wellbender.ui.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

@AndroidEntryPoint
class ExploreFragment :
    BaseFragment<FragmentExploreBinding, ExploreViewModel>(R.layout.fragment_explore) {
    override fun getViewModelClass() = ExploreViewModel::class.java


    private var blogWriteAdapter: BlogWriteAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initBlogWritesAdapter()
    }

    private fun initObservers() {
        viewModel.blogWriteList.observe(viewLifecycleOwner) {
            blogWriteAdapter?.submitList(it)
        }
    }

    private fun initBlogWritesAdapter() {
        blogWriteAdapter = BlogWriteAdapter()
        binding.recyclerViewWrites.layoutManager = LinearLayoutManager(requireActivity())

        blogWriteAdapter?.onClickRead = {
            // todo : on click read
            viewModel.navigate(MainFragmentDirections.actionMainFragmentToReadBlogFragment("0"))
        }
        blogWriteAdapter?.onClickBookmark = { item, mark ->
            // todo : on click book mark
        }

        binding.recyclerViewWrites.adapter = blogWriteAdapter
    }

    companion object {
        fun newInstance(): ExploreFragment {
            val fragment = ExploreFragment()

            return fragment
        }
    }
}