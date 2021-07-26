package com.aliyoungprog.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliyoungprog.R
import com.aliyoungprog.data.database.MyFirebaseAuth
import com.aliyoungprog.databinding.AllProductsFragmentBinding
import com.aliyoungprog.domain.entity.Product
import com.aliyoungprog.presentation.adapters.ItemClickListener
import com.aliyoungprog.presentation.adapters.NewProductAdapter
import com.aliyoungprog.presentation.vm.ProductsViewModel
import kotlinx.android.synthetic.main.all_products_fragment.*
import kotlinx.android.synthetic.main.all_products_fragment.view.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AllProductsFragment : Fragment(), ItemClickListener {


    private val productsViewModel: ProductsViewModel by viewModel()
    var adapter: NewProductAdapter = NewProductAdapter(arrayListOf<Product>(), this)
    lateinit var binding: AllProductsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllProductsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.all_products.layoutManager = LinearLayoutManager(context)
        setUpViewModel()
        observeBooks()
        setUpSearchView()
    }

    companion object {
        @JvmStatic
        fun getInstance() = AllProductsFragment()
    }

    private fun setUpViewModel(){
        binding.viewModel = productsViewModel
        productsViewModel.getAllProducts()
    }

    private fun observeBooks(){
        binding.progressBar.visibility = View.VISIBLE
        all_products.adapter = adapter
        productsViewModel.productsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.products = (it as ArrayList<Product>)
            adapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        })
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

    private fun setUpSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()){
                    productsViewModel.getAllProducts()
                    return true
                }
                adapter.getFilter().filter(newText);
                return true
            }

        })
    }
}
