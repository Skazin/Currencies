package com.currencies.shared.data.datasource.database.currencies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.currencies.model.UiRate

@Entity(tableName = "favourite_currencies", indices = [Index("row_name"), Index("row_rate")])
data class FavouriteCurrenciesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "row_id") val id: Long = 0,
    @ColumnInfo(name = "row_name") val name: String,
    @ColumnInfo(name = "row_rate") val rate: Double
) {
    fun toUiRate(): UiRate = UiRate(
        id = this.id,
        currency = this.name,
        rate = this.rate,
        isFavourite = true
    )
}