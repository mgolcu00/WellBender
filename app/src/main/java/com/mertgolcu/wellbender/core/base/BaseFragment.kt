package com.mertgolcu.wellbender.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mertgolcu.wellbender.BR

/**
 * Created by Mert Gölcü on 15.12.2022.
 */

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {
    lateinit var binding: B
    lateinit var viewModel: VM

    // for other view model support abstraction
    protected abstract fun getViewModelClass(): Class<VM>

    // for binding
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[getViewModelClass()]

        arguments?.let(::initExtras)
    }

    private fun initExtras(bundle: Bundle) {
        viewModel.fetchExtras(bundle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            rootView = binding.root
             binding.setVariable(BR.viewModel, viewModel) // set data binding viewModels from root
            binding.lifecycleOwner = viewLifecycleOwner
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.executePendingBindings() // execution for view model variable importing

        // view model event observes
        initBaseViewEventObserver()
    }

    private fun initBaseViewEventObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.baseEvent.collect {
                onViewEvent(it)
            }
        }
    }

    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            is BaseViewEvent.Navigate -> {
                findNavController().navigate(event.directions)
            }
            is BaseViewEvent.NavigateBack -> {
                findNavController().popBackStack()
                requireView().clearFocus()
            }
            is BaseViewEvent.NavigateBackWithData -> {
                if (event.result != null) {
                    navigateBackWithResult(
                        event.destination,
                        event.result.second,
                        event.result.first
                    )
                } else {
                    event.destination?.let { findNavController().popBackStack(it, false) }
                }
            }
            is BaseViewEvent.ShowMessage -> {
                showMessage(event.message)
            }
            is BaseViewEvent.ShowMessageWithRes -> {
                showMessage(getString(event.messageId))
            }
        }
    }

    private fun navigateBackWithResult(destination: Int?, result: Bundle, key: String) {
        activity?.supportFragmentManager?.setFragmentResult(key, result)
        if (destination != null)
            findNavController().popBackStack(destination, false)
        else {
            findNavController().popBackStack()
        }
    }

    private fun showMessage(msg: String) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_LONG).show()
    }


}