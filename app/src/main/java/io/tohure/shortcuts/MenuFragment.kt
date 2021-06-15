package io.tohure.shortcuts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.tohure.shortcuts.databinding.FragmentMenuBinding
import java.text.SimpleDateFormat
import java.util.*

private const val SUPPORT_FEATURE = "supportBinding"
private const val TIME_FEATURE = "viewTime"

private const val PARAM_FEATURE = "featureParam"

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
        val newQueryFeature = "${activity?.intent?.extras?.getString(PARAM_FEATURE)}"

        Log.d("tohure", newQueryFeature)

        when (newQueryFeature) {
            SUPPORT_FEATURE -> findNavController().navigate(R.id.flowThreeFragment)
            TIME_FEATURE -> callTimeDialog()
        }
    }

    private fun callTimeDialog() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        val sdf = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateandTime: String = sdf.format(Date())


        builder?.setMessage(currentDateandTime)
            ?.setTitle("The time!")
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}