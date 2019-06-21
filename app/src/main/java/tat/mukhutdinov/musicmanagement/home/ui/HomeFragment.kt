package tat.mukhutdinov.musicmanagement.home.ui

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SCROLL_STATE_IDLE
import kotlinx.android.synthetic.main.home.image
import kotlinx.android.synthetic.main.home.pager
import kotlinx.android.synthetic.main.home.tabs
import org.koin.android.viewmodel.ext.android.viewModel
import tat.mukhutdinov.musicmanagement.R
import tat.mukhutdinov.musicmanagement.albumssaved.ui.AlbumsSavedFragment
import tat.mukhutdinov.musicmanagement.artistserarch.ui.ArtistSearchFragment
import tat.mukhutdinov.musicmanagement.databinding.HomeBinding
import tat.mukhutdinov.musicmanagement.infrastructure.common.ui.BaseFragment

class HomeFragment : BaseFragment<HomeViewModelBinding, HomeBinding>() {

    override val viewModel: HomeViewModel by viewModel<HomeLifecycleAwareViewModel>()

    override val layoutId: Int = R.layout.home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entries = listOf(
            PagerEntry(getString(R.string.saved_albums), AlbumsSavedFragment(), getDrawable(R.drawable.album)),
            PagerEntry(getString(R.string.artists), ArtistSearchFragment(), getDrawable(R.drawable.artist))
        )

        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, entries)

        pager.adapter = homeViewPagerAdapter
        tabs.setupWithViewPager(pager)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var sum = 0f
            var oldPosition = 0

            override fun onPageScrollStateChanged(state: Int) {
                if (state == SCROLL_STATE_IDLE) {
                    oldPosition = pager.currentItem
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val first: Drawable? = entries[position].image

                if (positionOffset == 0f) {
                    image.setImageDrawable(first)
                    return
                }

                val second: Drawable? = entries[position + 1].image

                first?.alpha = (255 * (1f - positionOffset)).toInt()
                second?.alpha = (255 * (positionOffset)).toInt()

                val drawables = if (position + positionOffset > sum) { // moving to the right
                    arrayOf(first, second)
                } else {
                    arrayOf(second, first)
                }

                sum = position + positionOffset

                val finalDrawable = LayerDrawable(drawables)
                finalDrawable.setLayerInset(0, 0, 0, 0, 0)
                finalDrawable.setLayerInset(1, 0, 0, 0, 0)

                image.setImageDrawable(finalDrawable)
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    private fun getDrawable(@DrawableRes drawable: Int) =
        ContextCompat.getDrawable(requireContext(), drawable)

    class HomeViewPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: List<PagerEntry>
    ) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment =
            fragments[position].fragment

        override fun getCount(): Int =
            fragments.size

        override fun getPageTitle(position: Int): CharSequence? =
            fragments[position].title
    }

    class PagerEntry(val title: String, val fragment: Fragment, val image: Drawable?)
}