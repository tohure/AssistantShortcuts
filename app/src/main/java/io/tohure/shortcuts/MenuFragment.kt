package io.tohure.shortcuts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.tohure.shortcuts.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        setupClicks()
        return binding.root
    }

    private fun setupClicks() {

        binding.btnNote.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToFlowOneFragment()
            findNavController().navigate(action)
        }

        binding.btnNews.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToFlowTwoFragment()
            findNavController().navigate(action)
        }

        binding.btnSupport.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToFlowThreeFragment()
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}