package com.sp.corefeatures

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sp.common.entities.NodeLink
import com.sp.common.entities.SearchItem
import com.sp.network.SpaceImageService

class ImagePagingSource(
    private val backend: SpaceImageService,
    private val query: String
) : PagingSource<Int, SearchItem>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, SearchItem> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = backend.getImages(query,nextPageNumber)
            return LoadResult.Page(
                data = response.collection.items,
                prevKey = null, // Only paging forward.
                nextKey = getPage(response.collection.links[0])
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            return LoadResult.Error(e)
        }
    }

    private fun getPage(get: NodeLink): Int? {
        return null
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
