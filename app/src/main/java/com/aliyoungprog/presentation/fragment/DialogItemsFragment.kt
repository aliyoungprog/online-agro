package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.aliyoungprog.R
import com.aliyoungprog.databinding.FragmentDialogItemsBinding

class DialogItemsFragment : DialogFragment(), View.OnClickListener {

    lateinit var binding: FragmentDialogItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogItemsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
    }

    companion object {

        @JvmStatic
        fun getInstance() = DialogItemsFragment()
    }

    override fun onClick(v: View?) {
        changeFragment(AllProductsFragment.getInstance())
    }

    private fun setUpOnClickListeners(){
        binding.berriesBtn.setOnClickListener(this)
        binding.flowersBtn.setOnClickListener(this)
        binding.fruitsBtn.setOnClickListener(this)
        binding.vegetablesBtn.setOnClickListener(this)
        binding.conifersBtn.setOnClickListener(this)
    }

    private fun changeFragment(fragmentObject: Fragment) {
        val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
        fragmentManager?.replace(R.id.nav_host_fragment, fragmentObject)
        fragmentManager?.commit()
    }
}
