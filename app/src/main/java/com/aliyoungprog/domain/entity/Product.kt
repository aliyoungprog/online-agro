package com.aliyoungprog.domain.entity

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Product (
    val name: String? = null,
    val category: String? = null,
    val userEmail: String? = null

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }
        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}
