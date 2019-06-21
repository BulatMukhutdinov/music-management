package tat.mukhutdinov.musicmanagement.album.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.album.tracks
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.album.ui.adapter.TrackDiffUtilCallback
import tat.mukhutdinov.musicmanagement.album.ui.adapter.TracksAdapter
import tat.mukhutdinov.musicmanagement.databinding.AlbumBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseFragment

class AlbumFragment : BaseFragment<AlbumViewModelBinding, AlbumBinding>() {

    override val viewModel: AlbumViewModel by viewModel<AlbumLifecycleAwareViewModel> { parametersOf(args) }

    override val layoutId: Int = R.layout.album

    private val args: AlbumFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTracksList()

        viewModel.navigateUp.observe(viewLifecycleOwner, Observer { navigateUp() })

        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val maxHeight = appBarLayout.height - binding.toolbar.height
            val fraction = (maxHeight + verticalOffset) / maxHeight.toFloat()

            binding.cover.alpha = fraction
        })

        binding.cover.clipToOutline = true
    }

    private fun setupTracksList() {
        val adapter = TracksAdapter(viewModel.binding)

        tracks.adapter = adapter
        tracks.layoutManager = LinearLayoutManager(context)
        tracks.setHasFixedSize(true)

        viewModel.tracks.observe(viewLifecycleOwner, Observer {
            val albumDiffUtilCallback = TrackDiffUtilCallback(adapter.tracks, it)
            val diffResult = DiffUtil.calculateDiff(albumDiffUtilCallback)

            adapter.tracks.clear()
            adapter.tracks.addAll(it)
            diffResult.dispatchUpdatesTo(adapter)
        })
    }
}