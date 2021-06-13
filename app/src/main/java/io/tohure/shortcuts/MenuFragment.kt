package io.tohure.shortcuts

import android.net.Uri
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClicks()
    }

    private fun setupClicks() {

        binding.btnNote.setOnClickListener {
            findNavController().navigate(R.id.flowOneFragment)
        }

        binding.btnNews.setOnClickListener {
            findNavController().navigate(R.id.flowTwoFragment)
        }

        binding.btnSupport.setOnClickListener {
            findNavController().navigate(R.id.flowThreeFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}