package ua.oshevchuk.testrecyclerretrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.oshevchuk.testrecyclerretrofit.databinding.ItemBinding
import ua.oshevchuk.testrecyclerretrofit.models.GameModelItem
import ua.oshevchuk.testrecyclerretrofit.screens.main.MainFragment
import java.text.FieldPosition

class MainRecyclerAdapter(private val clickListener: (GameModelItem) -> Unit) :
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {
    private val mainFragment = MainFragment()
    class MainViewHolder(val binding: ItemBinding, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    private val gameCallback = object : DiffUtil.ItemCallback<GameModelItem>() {
        override fun areItemsTheSame(oldItem: GameModelItem, newItem: GameModelItem): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: GameModelItem, newItem: GameModelItem): Boolean {
            return newItem == oldItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(view) {
            clickListener(games[it])
        }
    }

    private val differ = AsyncListDiffer(this, gameCallback)
    var games: List<GameModelItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val game = games[position]
        holder.binding.apply {

            gameTitle.text = game.title
            gamePlatform.text = game.platform
        }
        Glide.with(holder.itemView).load(game.thumbnail).into(holder.binding.gameImage)
    }

    override fun getItemCount(): Int {
        return games.size
    }


}