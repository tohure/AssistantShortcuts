package io.tohure.shortcuts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.assistant.appactions.suggestions.client.AppShortcutIntent
import com.google.assistant.appactions.suggestions.client.AppShortcutSuggestion
import com.google.assistant.appactions.suggestions.client.AssistantShortcutSuggestionsClient

class FlowFourthFragment : Fragment() {

    lateinit var appShortcutIntent : AppShortcutIntent
    lateinit var shortcutsClient : AssistantShortcutSuggestionsClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().intent.removeExtra(PARAM_FEATURE)
        return inflater.inflate(R.layout.fragment_flow_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shortcutsClient = AssistantShortcutSuggestionsClient.builder()
            .setAgentUid("YOUR_AGENT_ID")
            .setContext(requireContext())
            .setVerifyIntents(true)
            .build()

        appShortcutIntent = AppShortcutIntent.builder()
            .setIntentName("actions.intent.OPEN_APP_FEATURE")
            .setPackageName("io.tohure.shortcuts")
            .setIntentParamName("feature")
            .setIntentParamValue("Assistant")
            .build()

        shortcutsClient.lookupShortcut(appShortcutIntent)
            .addOnSuccessListener {
                if (it.isShortcutPresent) {
                    Toast.makeText(requireContext(), "Recuerda que puedes acceder a este feature por Google Assistant :)", Toast.LENGTH_SHORT).show()
                    // app can remind that the user has a shortcut for this app action
                } else {
                    suggestShortcut()
                    Toast.makeText(requireContext(), "Shortcut can be suggested", Toast.LENGTH_SHORT).show()
                    // app can suggest to create a shortcut
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Shortcut promo Failed", Toast.LENGTH_SHORT).show()
                Log.e("tohure", "Shortcut lookup failed", it)
            }
    }

    private fun suggestShortcut() {
        val orderShortcut = AppShortcutSuggestion.builder()
            .setAppShortcutIntent(appShortcutIntent)
            .setCommand("Open personal assistant")
            .build()

        shortcutsClient.createShortcutSuggestionIntent(orderShortcut)
            .addOnSuccessListener {
                activity?.application?.startActivity(it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                Toast.makeText(requireContext(), "Suggesting Shortcut", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Shortcut suggest Failed", Toast.LENGTH_SHORT).show()
                Log.e("tohure", "Shortcut suggest failed", it)
            }

    }
}