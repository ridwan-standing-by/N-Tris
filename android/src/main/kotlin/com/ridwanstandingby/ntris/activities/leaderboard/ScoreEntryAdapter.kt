package com.ridwanstandingby.ntris.activities.leaderboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.domain.ScoreEntry
import kotlinx.android.synthetic.main.score_entry_list_item.view.*

class ScoreEntryAdapter(private val context: Context)
    : RecyclerView.Adapter<ScoreEntryAdapter.ScoreEntryViewHolder>() {

    var items: List<ScoreEntry> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreEntryViewHolder =
            ScoreEntryViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.score_entry_list_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: ScoreEntryViewHolder, position: Int) {
        val item = items[position]

        viewHolder.number.text = (position + 1).toString()
        viewHolder.name.text = item.name
        viewHolder.lines.text = item.lines.toString()
        viewHolder.score.text = item.score.toString()
    }

    class ScoreEntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val number: TextView = view.scoreEntryListNumber
        val name: TextView = view.scoreEntryListName
        val lines: TextView = view.scoreEntryListLines
        val score: TextView = view.scoreEntryListScore
    }
}
