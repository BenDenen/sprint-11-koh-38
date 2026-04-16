package ru.practicum.sprint_11_koh_38

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.DateFormat

class NewsAdapter : RecyclerView.Adapter<NewsItemViewHolder>() {

    var items: List<NewsItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}


class NewsItemViewHolder(
    parentView: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parentView.context)
        .inflate(R.layout.v_news_item, parentView, false)
) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val created: TextView = itemView.findViewById(R.id.created)
    private val sportTitleView:TextView = itemView.findViewById<TextView>(R.id.sport_teams)
    private val scienceImage:ImageView = itemView.findViewById<ImageView>(R.id.science_img)

    fun bind(item: NewsItem) {
        title.text = item.title
        created.text =
            DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT
            ).format(item.created)

        when (item) {
            is NewsItem.SportNewsItem -> {
                sportTitleView.visibility = VISIBLE
                sportTitleView.text = item.specificPropertyForSport
                scienceImage.visibility = GONE
            }
            is NewsItem.ScienceNewsItem -> {
                sportTitleView.visibility = GONE
                scienceImage.visibility = VISIBLE
                Glide.with(scienceImage).load(item.specificPropertyForScience).into(scienceImage)
            }

            is NewsItem.DefaultNewsItem -> {
                sportTitleView.visibility = GONE
                scienceImage.visibility = GONE
            }
        }

//        item.specificPropertyForSport?.let {
//            sportTitleView.visibility = VISIBLE
//            sportTitleView.text = it
//        } ?: run {
//            sportTitleView.visibility = GONE
//        }
//
//        item.specificPropertyForScience?.let {
//            scienceImage.visibility = VISIBLE
//            Glide.with(scienceImage).load(it).into(scienceImage)
//        } ?: run {
//            scienceImage.visibility = GONE
//        }

    }
}