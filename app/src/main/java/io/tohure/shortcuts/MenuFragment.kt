package io.tohure.shortcuts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.tohure.shortcuts.databinding.FragmentMenuBinding
import java.text.DateFormat
import java.util.Date

private const val SUPPORT_FEATURE = "supportBinding"
private const val TIME_FEATURE = "viewTime"
private const val ASSISTANT_FEATURE = "assistant"

const val PARAM_FEATURE = "featureParam"

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
        manageAppFeature()
    }

    private fun manageAppFeature() {
        val newQueryFeature = "${requireActivity().intent?.extras?.getString(PARAM_FEATURE)}"

        when (newQueryFeature) {
            SUPPORT_FEATURE -> findNavController().navigate(R.id.flowThreeFragment)
            ASSISTANT_FEATURE -> findNavController().navigate(R.id.flowFourthFragment)
            TIME_FEATURE -> callTimeDialog()
        }
    }

    private fun callTimeDialog() {
        val builder = activity?.let {
            AlertDialog.Builder(it)
        }

        val dateInstance = DateFormat.getDateInstance(DateFormat.LONG)
        val currentDateAndTime = dateInstance.format(Date())

        builder?.setMessage(currentDateAndTime)
            ?.setTitle("The Current time!")

        val dialog: AlertDialog? = builder?.create()

        dialog?.show()
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

        binding.fbAssistant.setOnClickListener {
            findNavController().navigate(R.id.flowFourthFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}