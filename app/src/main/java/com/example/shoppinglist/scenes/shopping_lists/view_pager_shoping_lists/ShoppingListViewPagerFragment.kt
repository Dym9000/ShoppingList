package com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentShoppingListViewPagerBinding
import com.example.shoppinglist.scenes.shopping_lists.common.interfaces.OnSceneChange
import com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists.util.ViewPagerTabs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListViewPagerBinding

    private lateinit var onSceneChangedListener: OnSceneChange

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSceneChangedListener = context as OnSceneChange
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shopping_list_view_pager, container, false
        )

        val viewPager = binding.viewPagerShoppingListVp
        val tabLayout = binding.tabsShoppingListVp

        viewPager.adapter = ShoppingListViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        setToolbar()

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            ViewPagerTabs.CURRENT_LIST -> R.drawable.ic_baseline_list_24
            ViewPagerTabs.ARCHIVED_LIST -> R.drawable.ic_baseline_archive_24
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            ViewPagerTabs.CURRENT_LIST -> getString(R.string.current_list_tab_title)
            ViewPagerTabs.ARCHIVED_LIST -> getString(R.string.archived_list_tab_title)
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun setToolbar() {
        onSceneChangedListener.apply {
            setToolbarExpanded(true)
            setToolbarTitle(getString(R.string.view_pager_list_toolbar_title))
        }
    }

}