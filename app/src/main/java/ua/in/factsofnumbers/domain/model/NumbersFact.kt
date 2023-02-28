package ua.`in`.factsofnumbers.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "facts")
data class NumbersFact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val number: Long,
    val description: String
) : Parcelable