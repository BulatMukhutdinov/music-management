package tat.mukhutdinov.musicmanagement.albumssaved.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.albums_saved.albums
import org.koin.android.viewmodel.ext.android.viewModel
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.albumssaved.ui.adapter.AlbumsSavedAdapter
import tat.mukhutdinov.musicmanagement.databinding.AlbumsSavedBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseFragment
import tat.mukhutdinov.musicmanagement.infrastructure.util.ui.SwipeToDeleteCallback

class AlbumsSavedFragment : BaseFragment<AlbumsSavedViewModelBinding, AlbumsSavedBinding>() {

    override val layoutId: Int = R.layout.albums_saved

    override val viewModel: AlbumsSavedViewModel by viewModel<AlbumsSavedLifecycleAwareViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AlbumsSavedAdapter(viewModel.binding)

        albums.adapter = adapter
        albums.layoutManager = LinearLayoutManager(context)
        albums.setHasFixedSize(true)

        val swipeHandler = object : SwipeToDeleteCallback(view.context) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                viewModel.onSwiped(adapter.currentList?.get(viewHolder.adapterPosition))
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(albums)

        viewModel.albums.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.onAlbumDelete.observe(viewLifecycleOwner, Observer {
            val message = getString(R.string.on_album_deleted, it)
            Toast.makeText(context, message, LENGTH_SHORT).show()
        })

        viewModel.directions.observe(viewLifecycleOwner, Observer { navigateTo(it) })
    }
}