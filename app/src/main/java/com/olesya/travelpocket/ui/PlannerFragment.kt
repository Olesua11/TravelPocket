package com.olesya.travelpocket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.olesya.travelpocket.databinding.FragmentPlannerBinding

class PlannerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        return FragmentPlannerBinding.inflate(inflater, container, false).root
    }
}
