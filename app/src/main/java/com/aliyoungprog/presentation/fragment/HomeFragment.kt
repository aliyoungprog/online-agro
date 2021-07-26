package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliyoungprog.R
import com.aliyoungprog.data.database.MyFirebaseAuth
import com.aliyoungprog.databinding.HomeFragmentBinding
import com.aliyoungprog.domain.entity.Product
import com.aliyoungprog.presentation.adapters.ItemClickListener
import com.aliyoungprog.presentation.adapters.NewProductAdapter
import com.aliyoungprog.presentation.vm.ProductsViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment(), ItemClickListener {


    var adapter: NewProductAdapter = NewProductAdapter(arrayListOf(), this)

    private val productsViewModel: ProductsViewModel by viewModel()
    lateinit var binding: HomeFragmentBinding
    private val firebaseAuth = MyFirebaseAuth

    companion object{
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        view.user_products.layoutManager = LinearLayoutManager(context)
        setUpViewModel()
        observeBooks()

        binding.addNewCategoryBtn.setOnClickListener{
            DialogItemsFragment.getInstance().show(childFragmentManager, "test")
        }
    }


    private fun observeBooks(){
        progressBar.visibility = View.VISIBLE
        user_products.adapter = adapter
        productsViewModel.allUserProducts.observe(viewLifecycleOwner) {
            if (it.isEmpty()) empty_text.visibility = View.VISIBLE
            else empty_text.visibility = View.GONE
            adapter.products = (it as ArrayList<Product>)
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setUpViewModel(){
        binding.viewModel = productsViewModel
        productsViewModel.getAllUserProducts(firebaseAuth.db_auth.currentUser?.email!!)
    }

    override fun onItemClicked(product: Product) {
        setUpBundle(product)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        val fragment = ProductsDescriptionFragment.newInstance()
        fragment.arguments = bundle
        transaction?.replace(R.id.nav_host_fragment, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun setUpBundle(product: Product){
        setFragmentResult("getProductName", bundleOf("product_name" to product.name, "product_desc" to product.category))
    }
}
