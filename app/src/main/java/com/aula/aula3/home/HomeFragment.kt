package com.aula.aula3.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.aula.aula3.R
import com.aula.aula3.databinding.HomeFragmentBinding
import com.aula.aula3.domain.model.CompanyInfoDomainModel
import com.aula.aula3.domain.model.LaunchDomainModel
import com.aula.aula3.home.adapter.LaunchesAdapter
import com.aula.aula3.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()
    private val binding by viewBinding(HomeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        with(viewLifecycleOwner.lifecycleScope) {
            launch {
                viewModel.company.collect {
                    setupCompanyInfo(it)
                }
            }

            launch {
                viewModel.launches.collect {
                    hideLoading()
                    hideFeedback()
                    launchesIsEmpty(it)
                }
            }
        }
    }


    private fun hideLoading() {
        binding.customLoading.progress.isVisible = false
    }

    private fun showEmpty() {
        binding.recycler.isVisible = false
        binding.customFeedback.imageFeedback.apply {
            isVisible = true
            loadDrawable(requireContext(), R.drawable.ic_empty, getString(R.string.feedback_empty_image))
        }

        binding.customFeedback.feedback.apply {
            isVisible = true
            text = getString(R.string.feedback_empty)
        }
    }

    private fun hideFeedback() {
        binding.recycler.isVisible = true
        binding.customFeedback.imageFeedback.isVisible = false
        binding.customFeedback.feedback.isVisible = false
    }


    private fun launchesIsEmpty(launchUiModel: List<LaunchDomainModel>) {
        if (launchUiModel.isEmpty())
            showEmpty()
        else
            setupAdapter(launchUiModel)
    }

    private fun setupCompanyInfo(companyInfoUiModel: CompanyInfoDomainModel) =
        with(companyInfoUiModel) {
            if (name.isBlank()) return@with
            binding.companyDescription.text = getString(
                R.string.company_description, name, founder, founded,
                employees, launchSites, valuation
            )
        }


    private fun setupAdapter(launchesList: List<LaunchDomainModel>) {
        binding.recycler.adapter = LaunchesAdapter(launchesList) { links ->
            openCustomTabsIntent(links.videoLink)
        }
    }

    private fun openCustomTabsIntent(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
    }
}

fun ImageView.loadDrawable(context: Context, drawable: Int, description: String) {
    setImageDrawable(
        ContextCompat.getDrawable(
            context,
            drawable
        )
    )
    contentDescription = description
}

