package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.aliyoungprog.databinding.FragmentVegetablesBinding

class AllProductsFragment : Fragment() {

    lateinit var bind: FragmentVegetablesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentVegetablesBinding.inflate(inflater)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun getInstance() = AllProductsFragment()
    }
}
