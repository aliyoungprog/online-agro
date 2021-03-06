package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.aliyoungprog.R

class MoonCalendarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_moon_calendar, container, false)
    }

    companion object {
        @JvmStatic
        fun getInstance() = MoonCalendarFragment()
    }
}
