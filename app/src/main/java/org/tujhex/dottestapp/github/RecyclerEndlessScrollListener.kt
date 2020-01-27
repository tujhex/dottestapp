package org.tujhex.dottestapp.github

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * @author tujhex
 * since 28.01.20
 */
class RecyclerEndlessScrollListener : RecyclerView.OnScrollListener() {
    private val threshold = 5
    var isLoading: Boolean = false
    var hasMorePages: Boolean = true

    //TODO(Add cache data source 4 ; add use case 2 fetch more; add use case 2 fetch cached data - or mb mediator data? )
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val manager = recyclerView.layoutManager
        if (manager is LinearLayoutManager) {


            val visibleItemCount = manager.childCount
            val totalItemCount = manager.itemCount
            val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
            if (!isLoading && hasMorePages) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - threshold) {
                    loadMoreItems()
                }
            }
        }

    }
}