package ua.oshevchuk.testrecyclerretrofit.screens.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ua.oshevchuk.testrecyclerretrofit.R
import ua.oshevchuk.testrecyclerretrofit.adapters.MainRecyclerAdapter
import ua.oshevchuk.testrecyclerretrofit.core.BaseFragment
import ua.oshevchuk.testrecyclerretrofit.databinding.FragmentMainBinding
import ua.oshevchuk.testrecyclerretrofit.models.GameModelItem


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var adapter: MainRecyclerAdapter
    private lateinit var mainViewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initViewModel()


    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.getGamesList().observe(viewLifecycleOwner) {
            adapter.games = it
        }


    }


    private fun initAdapter() {
        adapter = MainRecyclerAdapter {
            onItemClicked(it)
        }
        binding.mainRecycler.adapter = adapter
        binding.mainRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onItemClicked(gameModelItem: GameModelItem) {
        val bundle = Bundle()
        bundle.putSerializable("game", gameModelItem)
        requireActivity().findNavController(R.id.fragmentContainerView).navigate(R.id.action_mainFragment_to_propertiesFragment, bundle)
    }

}