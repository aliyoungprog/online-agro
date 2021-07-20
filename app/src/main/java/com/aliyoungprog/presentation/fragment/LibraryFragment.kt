package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.aliyoungprog.R

class LibraryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    companion object {
        @JvmStatic
        fun getInstance() = LibraryFragment()
    }
}
