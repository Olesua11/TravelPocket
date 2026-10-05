package com.olesya.travelpocket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.olesya.travelpocket.databinding.FragmentBudgetBinding

class BudgetFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View {
        return FragmentBudgetBinding.inflate(inflater, container, false).root
    }
}
