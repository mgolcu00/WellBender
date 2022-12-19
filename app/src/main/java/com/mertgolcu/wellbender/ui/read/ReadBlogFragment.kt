package com.mertgolcu.wellbender.ui.read

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mertgolcu.wellbender.R
import com.mertgolcu.wellbender.core.base.BaseFragment
import com.mertgolcu.wellbender.databinding.FragmentReadBlogBinding
import com.mertgolcu.wellbender.ui.read.adapter.BlogCardAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mert Gölcü on 19.12.2022.
 */

@AndroidEntryPoint
class ReadBlogFragment :
    BaseFragment<FragmentReadBlogBinding, ReadBlogViewModel>(R.layout.fragment_read_blog) {
    override fun getViewModelClass() = ReadBlogViewModel::class.java

    private var blogCardAdapter: BlogCardAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()

        // temp test bg image

    }

    private fun initObservers() {
        viewModel.currentPage.observe(viewLifecycleOwner) {
            // progress
            /*
            a = 10 // total
            b = 2 // current
            10 ->2
            100 -> x
            10*x = 100*2
            x = 100*2 / 10
            x = (100*current)/total
             */
            val percent = (100 * it) / viewModel.totalPage
            binding.linearIndicator.progress = percent

            // view pager
            binding.viewPagerCards.currentItem = it -1

            if (it == viewModel.totalPage) {
                onDoneReading()
            } else {
                initUI()
            }
        }

        viewModel.cardList.observe(viewLifecycleOwner) { list ->
            blogCardAdapter?.let { adapter ->
                adapter.submitList(list)
            }
        }

    }


    private fun initUI() {
        binding.buttonLeft.setOnClickListener {
            viewModel.prevPage()
        }
        binding.buttonRight.setImageResource(R.drawable.ic_chevron_right)
        binding.buttonRight.setOnClickListener {
            viewModel.nextPage()
        }
    }

    private fun initAdapter() {
        blogCardAdapter = BlogCardAdapter()
        binding.viewPagerCards.adapter = blogCardAdapter
        binding.viewPagerCards.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.currentPage.postValue(position + 1)
            }
        })
    }

    private fun onDoneReading() {
        binding.buttonRight.setImageResource(R.drawable.ic_done)
        binding.buttonRight.setOnClickListener {
            viewModel.popBackStack()
        }
    }

}