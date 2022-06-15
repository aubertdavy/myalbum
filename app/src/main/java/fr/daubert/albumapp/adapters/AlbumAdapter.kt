package fr.daubert.albumapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.daubert.albumapp.R
import fr.daubert.albumapp.data.Album
import fr.daubert.albumapp.databinding.ListItemAlbumBinding

class AlbumAdapter(
    private val context: Context,
    private val albums: List<Album>
    ) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ListItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        with(holder){
            with(albums[position]) {
                binding.tvTitle.text = this.title

                Glide.with(context)
                    .load(this.thumbnailUrl.isNullOrEmpty())
                    .centerCrop().placeholder(R.drawable.placehoder)
                    .into(binding.ivAlbum)
            }
        }
    }

    inner class AlbumViewHolder(val binding: ListItemAlbumBinding) :RecyclerView.ViewHolder(binding.root)
}