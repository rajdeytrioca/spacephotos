package com.sp.corefeatures

import android.net.Uri
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
            val nextPageNumber = params.key ?: 1
            val response = backend.getImages(query,nextPageNumber)
            return LoadResult.Page(
                data = response.collection.items,
                prevKey = null,
                nextKey = getPage(response.collection.links[0])
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun getPage(node: NodeLink): Int? {
        val uri = Uri.parse(node.href)
        val segment = uri.getQueryParameter("page")
        return segment?.toInt()
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
