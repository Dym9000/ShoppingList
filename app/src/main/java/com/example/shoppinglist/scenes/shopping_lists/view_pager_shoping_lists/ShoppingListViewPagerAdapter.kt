package com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppinglist.scenes.shopping_lists.archived_list.ArchivedShoppingListFragment
import com.example.shoppinglist.scenes.shopping_lists.current_list.CurrentShoppingListFragment
import com.example.shoppinglist.scenes.shopping_lists.view_pager_shoping_lists.util.ViewPagerTabs

class ShoppingListViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Map of tab pages associated with their respective fragments
     */
    private val pages: Map<Int, () -> Fragment> = mapOf(
        ViewPagerTabs.CURRENT_LIST to { CurrentShoppingListFragment() },
        ViewPagerTabs.ARCHIVED_LIST to { ArchivedShoppingListFragment() }
    )

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}