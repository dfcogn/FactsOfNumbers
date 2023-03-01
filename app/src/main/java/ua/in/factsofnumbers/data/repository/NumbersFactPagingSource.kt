package ua.`in`.factsofnumbers.data.repository

import android.content.res.Resources
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.`in`.factsofnumbers.R
import ua.`in`.factsofnumbers.data.repository.datasource.LocalDataSource
import ua.`in`.factsofnumbers.domain.model.NumbersFact

class NumbersFactPagingSource(
    private val dataSource: LocalDataSource,
    private val resources: Resources
): PagingSource<Int, NumbersFact>() {
    override fun getRefreshKey(state: PagingState<Int, NumbersFact>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NumbersFact> {
        val pageIndex = params.key ?: 0

        return try {
            val response = dataSource.getAllSavedFactsPaging(params.loadSize, pageIndex * params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (response.size == params.loadSize) pageIndex + (params.loadSize / pageIndex) else null
            )
        } catch (ex: Exception) {
            LoadResult.Error(Exception(resources.getString(R.string.failed_to_load_numbers_fact_history)))
        }
    }
}