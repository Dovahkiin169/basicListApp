package com.omens.basiclistapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omens.basiclistapp.placeholder.PlaceholderContent

class ListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var adapter: MyItemRecyclerViewAdapter
    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        adapter = MyItemRecyclerViewAdapter(listener!!,viewModel)
        adapter.reloadList(PlaceholderContent.ITEMS)
        if (view is ConstraintLayout) {
            with(view.findViewById(R.id.list) as RecyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = this@ListFragment.adapter
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    class ListViewModel : ViewModel() {
        lateinit var placeholderItem: PlaceholderContent.PlaceholderItem
    }
}