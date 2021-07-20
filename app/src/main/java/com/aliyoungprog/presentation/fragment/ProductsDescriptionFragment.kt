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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class ProductsDescriptionFragment : Fragment() {

    lateinit var binding: FragmentBookDescriptionBinding
    lateinit var bookTitle: TextView
    lateinit var bookDesc: TextView
    lateinit var bookImage: ImageView
    lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentBookDescriptionBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
    companion object {
        fun newInstance() = ProductsDescriptionFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVars()
        receiveDataListener()
        //setUpActionBar(product.category.toString())
    }

    private fun setUpActionBar(bookName: String) {
        bookTitle.text = bookName
    }

    private fun receiveDataListener(){
        val bundle: Bundle? = this.arguments
        //product = bundle!!.getParcelable("product")!!
        //bookName.text = product.name
        //bookSender.text = product.category
        //bookDesc.text = product.description
        //downloadImg(product.name.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initVars(){
        //bookName = binding.bookDescriptionName
        //bookSender = binding.bookDescriptionAdd
        bookDesc = binding.bookDescriptionDesc
        bookTitle = binding.title
        //bookImage = binding.bookDescriptionImg
        bookTitle.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun downloadImg(book_name: String) {
        if(activity == null) return
        CoroutineScope(Dispatchers.IO).launch {
            val storage = MyFirebaseStorage.db_storage.getReference(book_name)
            Timber.tag("data").i(storage.getBytes(1024 * 1024).toString())
            storage.getBytes(1024 * 1024).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                bookImage.setImageBitmap(bitmap)
                context?.let { it1 ->
                    Glide.with(it1)
                        .load(bitmap)
                        .centerCrop()
                        .into(bookImage)
                }
            }.addOnFailureListener {

            }
        }
    }

}
