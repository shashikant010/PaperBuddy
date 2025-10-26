package com.example.paperbuddy.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paperbuddy.R
import com.example.paperbuddy.model.QuestionPaper
import java.text.SimpleDateFormat
import java.util.Locale

// We use ListAdapter for better performance with DiffUtil
class PaperListAdapter(
    private val onClick: (QuestionPaper) -> Unit
) : ListAdapter<QuestionPaper, PaperListAdapter.PaperViewHolder>(PaperDiffCallback) {

    // 1. ViewHolder: Holds the views for a single item
    class PaperViewHolder(itemView: View, val onClick: (QuestionPaper) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        // Get references to the views in item_paper.xml
        private val titleView: TextView = itemView.findViewById(R.id.text_paper_title)
        private val topicView: TextView = itemView.findViewById(R.id.text_paper_topic)
        private val detailsView: TextView = itemView.findViewById(R.id.text_paper_details)
        // private val iconView: ImageView = itemView.findViewById(R.id.icon_paper) // For later

        private var currentPaper: QuestionPaper? = null
        private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

        init {
            itemView.setOnClickListener {
                currentPaper?.let { paper ->
                    onClick(paper)
                }
            }
        }

        // 2. Bind: Connects data to the views
        fun bind(paper: QuestionPaper) {
            currentPaper = paper

            titleView.text = paper.title
            topicView.text = paper.topic

            val dateString = dateFormat.format(paper.timestamp)
            detailsView.text = "${paper.questionCount} Questions • $dateString"
        }
    }

    // 3. onCreateViewHolder: Inflates the item_paper.xml layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paper, parent, false)
        return PaperViewHolder(view, onClick)
    }

    // 4. onBindViewHolder: Calls the bind function for the item at that position
    override fun onBindViewHolder(holder: PaperViewHolder, position: Int) {
        val paper = getItem(position)
        holder.bind(paper)
    }
}

// 5. DiffUtil: Helps the adapter efficiently update the list
object PaperDiffCallback : DiffUtil.ItemCallback<QuestionPaper>() {
    override fun areItemsTheSame(oldItem: QuestionPaper, newItem: QuestionPaper): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QuestionPaper, newItem: QuestionPaper): Boolean {
        return oldItem == newItem
    }
}