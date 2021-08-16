package com.proway.mvvm.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.proway.mvvm.R
import com.proway.mvvm.adapter.ProductAdapter
import com.proway.mvvm.model.Product
import com.proway.mvvm.view_model.MainViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var productsRecyclerView: RecyclerView
    private val adapter = ProductAdapter {

    }

    private val productsObserver = Observer<List<Product>> { newList ->
        adapter.update(newList)
    }

    private val errorObserver = Observer<String> { error ->
        Snackbar.make(requireView(), error, Snackbar.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        productsRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner, productsObserver)
        viewModel.error.observe(viewLifecycleOwner, errorObserver)

        viewModel.fetchProdutos()
    }

}