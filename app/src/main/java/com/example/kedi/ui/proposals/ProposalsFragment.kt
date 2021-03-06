package com.example.kedi.ui.proposals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kedi.R


class ProposalsFragment : Fragment() {

    private lateinit var notificationsViewModel: ProposalsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(ProposalsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create, container, false)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)

    }
}