package com.aula.aula3.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.aula.aula3.R
import com.aula.aula3.databinding.LaunchesItemBinding
import com.aula.aula3.domain.model.LaunchDomainModel
import com.aula.aula3.domain.model.LinksDomainModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class LaunchesAdapter(private val launchList: List<LaunchDomainModel>,
                      private val onClick: ((LinksDomainModel) -> Unit)
) :
    RecyclerView.Adapter<LaunchesAdapter.HoursViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        val binding = LaunchesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HoursViewHolder(binding)
    }

    override fun getItemCount() = launchList.size

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        with(holder) {
            with(launchList[position]) {

                setupData(binding, this)
                setupIcon(binding, isLaunchSuccess)

                Glide.with(binding.root.context)
                    .load(links.missionPatchSmall)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_place_holder)
                    .into(binding.image)

                holder.itemView.setOnClickListener {
                    onClick(links)
                }
            }
        }
    }

    private fun setupData(binding: LaunchesItemBinding, launchUiModel: LaunchDomainModel) = with(launchUiModel) {
        binding.missionDescription.text = missionName
        binding.dateDescription.text = launchDate
        binding.rocketDescription.text = rocket.rocketName
        binding.daysTitle.text = binding.root.context.getString(getTitleSinceFrom(isPastLaunch))
        binding.daysDescription.text = "+/-$differenceOfDays"
    }

    private fun setupIcon(binding: LaunchesItemBinding, isLaunchSuccess: Boolean) {
        binding.statusMissionSuccess.visibility = if (isLaunchSuccess) View.VISIBLE else View.INVISIBLE
        binding.statusMissionFailed.isVisible = !isLaunchSuccess
    }

    private fun getTitleSinceFrom(isPastLaunch: Boolean) =
        if (isPastLaunch) {
            R.string.company_data_since
        } else {
            R.string.company_data_from
        }

    inner class HoursViewHolder(val binding: LaunchesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}
