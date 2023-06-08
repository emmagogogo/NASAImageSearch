package com.workday.takehometest

import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

data class NASAResult(
    val collection: Collection
)

data class Collection(
    val items: List<NASAItem>
)

data class NASAItem(
    val data: List<NASAData>,
    val links: List<NASALinks>,
)

data class NASAData(
    @Json(name = "nasa_id") val id: String,
    val title: String,
    val description: String,
    @Json(name = "date_created") val dateCreated: String,
)

data class NASALinks(
    val href: String,
)


data class NASAImage(
    val id: String,
    val title: String,
    val description: String,
    val dateCreated: LocalDate,
    val href: String,
) {
    companion object {
        fun fromNASAResult(nasaResult: NASAResult): List<NASAImage> {
            return nasaResult.collection.items.map {
                val data = it.data[0]
                val link = it.links[0]
                val dateTimePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
                val formatter = DateTimeFormatter.ofPattern(dateTimePattern).withZone(ZoneOffset.UTC)
                NASAImage(
                    id = data.id,
                    title = data.title,
                    description = data.description,
                    dateCreated = LocalDate.parse(data.dateCreated, formatter),
                    href = link.href,
                )
            }
        }
    }
}