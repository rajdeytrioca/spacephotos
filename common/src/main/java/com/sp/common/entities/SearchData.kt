package com.sp.common.entities

data class SearchData(
    val version: String,
    val href: String,
    val metadata: Metadata,
    val items: List<SearchItem>,
    val links:List<NodeLink>
)
