package org.tujhex.dottestapp.github

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.tujhex.dottestapp.core.spec.kotlin.get
import org.tujhex.dottestapp.core.spec.kotlin.safeOr


/**
 * @author tujhex
 * since 28.01.20
 */
class RecyclerEndlessScrollListener(
    private val isLoading: LiveData<Boolean>,
    private val hasMorePages: LiveData<Boolean>,
    private val currentPage: LiveData<Int>,
    private val pageConsumer: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
    private val threshold = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val manager = recyclerView.layoutManager
        if (manager is LinearLayoutManager) {
            val visibleItemCount = manager.childCount
            val totalItemCount = manager.itemCount
            val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
            if (!isLoading.value.get() && hasMorePages.value.get()) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - threshold) {
                    pageConsumer(currentPage.value.safeOr(0) + 1)
                }
            }
        }

    }
}