package com.omens.basiclistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omens.basiclistapp.databinding.FragmentItemBinding
import com.omens.basiclistapp.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val mListener: OnFragmentInteractionListener,
    private val viewModel: ListFragment.ListViewModel
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var values: List<PlaceholderItem> = listOf()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as PlaceholderItem
            viewModel.placeholderItem = item
            mListener.onListItemSelect()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content

        holder.updateView(item)

        with(holder.itemView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

        fun updateView(item: PlaceholderItem){
            idView.text = item.id
            contentView.text = item.content
        }
    }


    fun reloadList(items: List<PlaceholderItem>) {
        values = items
        notifyDataSetChanged()
    }
}