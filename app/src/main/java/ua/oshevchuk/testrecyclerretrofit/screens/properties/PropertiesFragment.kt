package ua.oshevchuk.testrecyclerretrofit.screens.properties

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ua.oshevchuk.testrecyclerretrofit.R
import ua.oshevchuk.testrecyclerretrofit.core.BaseFragment
import ua.oshevchuk.testrecyclerretrofit.databinding.FragmentPropertiesBinding
import ua.oshevchuk.testrecyclerretrofit.models.GameModelItem


class PropertiesFragment()
    : BaseFragment<FragmentPropertiesBinding>(FragmentPropertiesBinding::inflate) {
    private lateinit var currentGame:GameModelItem
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentGame = arguments?.getSerializable("game") as GameModelItem
        initInfo()
        Glide.with(requireActivity()).load(currentGame.thumbnail).into(binding.gameImage)
    }

    private fun initInfo() = with(binding) {
        gameTitle.text = currentGame.title
        gameDescription.text = currentGame.short_description
        gameGenre.text = currentGame.genre
        gamePublisher.text = currentGame.publisher
        gameYear.text = currentGame.release_date
    }
}