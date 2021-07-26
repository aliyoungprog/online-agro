package com.aliyoungprog.presentation.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.aliyoungprog.data.database.MyFirebaseStorage
import com.aliyoungprog.databinding.FragmentBookDescriptionBinding
import com.aliyoungprog.domain.entity.Product
import com.squareup.okhttp.internal.http.CacheStrategy
import kotlinx.android.synthetic.main.fragment_book_description.*
import kotlinx.android.synthetic.main.list_item_product.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ProductsDescriptionFragment : Fragment() {

    lateinit var binding: FragmentBookDescriptionBinding
    lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBookDescriptionBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = ProductsDescriptionFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        receiveDataListener()
    }

    private fun receiveDataListener(){
        val bundle: Bundle? = this.arguments
        product = bundle!!.getParcelable("product")!!
        product_description.text = product.category
        title.text = product.name
        product_name.text = product.name
        product_description.text = product.description
        title.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}
