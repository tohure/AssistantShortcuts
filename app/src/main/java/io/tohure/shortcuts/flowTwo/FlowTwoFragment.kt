package io.tohure.shortcuts.flowTwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.tohure.shortcuts.databinding.FragmentFlowTwoBinding

class FlowTwoFragment : Fragment() {

    private var _binding: FragmentFlowTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFlowTwoBinding.inflate(inflater, container, false)

        binding.rvFacts.apply {
            adapter = FactAdapter(Datasource.loadFacts())
            setHasFixedSize(true)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}