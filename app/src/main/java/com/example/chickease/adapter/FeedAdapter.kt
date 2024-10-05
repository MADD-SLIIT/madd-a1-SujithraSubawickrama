package com.example.chickease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chickease.R
import com.example.chickease.adapter.FlockAdapter.FlockViewHolder
import com.example.chickease.data_class.Feed


class FeedAdapter(private val feedList: List<Feed>, private val onFeedClick: (Feed) -> Unit) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val feedNo: TextView = itemView.findViewById(R.id.feedflock_no)
        val feedDate: TextView = itemView.findViewById(R.id.feed_date)
        val feedTime: TextView = itemView.findViewById(R.id.feed_time)
        val feedType: TextView = itemView.findViewById(R.id.feed_type)
        val feedQuantity: TextView = itemView.findViewById(R.id.feed_quntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flocklist, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedAdapter.FeedViewHolder, position: Int) {
        val feed = feedList[position]
        holder.feedNo.text = feed.feedFlockNo
        holder.feedDate.text = feed.feedDate
        holder.feedTime.text = feed.feedTime
        holder.feedType.text = feed.feedType
        holder.feedQuantity.text = feed.feedQuantity

        // Handle item click
        holder.itemView.setOnClickListener { onFeedClick(feed) }
    }

    override fun getItemCount(): Int = feedList.size
}
