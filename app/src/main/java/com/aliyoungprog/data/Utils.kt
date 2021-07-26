package com.aliyoungprog.data

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.aliyoungprog.R
import com.aliyoungprog.data.database.MyFirebaseStorage
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTime(): String {
    val russian = Locale("ru")
    val newMonths = arrayOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )
    val dfs: DateFormatSymbols = DateFormatSymbols.getInstance(russian)
    dfs.setMonths(newMonths)
    val df: DateFormat = DateFormat.getDateInstance(DateFormat.LONG, russian)
    val sdf: SimpleDateFormat = df as SimpleDateFormat
    sdf.setDateFormatSymbols(dfs)

    val jud: Date = Calendar.getInstance().time
    val month: String = sdf.format(jud)
    return month
}
fun downloadImgFromDb(icon_url: String, context: Context, imageView: ImageView) {
    CoroutineScope(Dispatchers.Main).launch {
        val storage = MyFirebaseStorage.db_storage.getReference(icon_url)
        storage.getBytes(1024 * 1024).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            //bookImage.setImageBitmap(bitmap)
            context?.let { it1 ->
                Glide.with(it1)
                    .load(bitmap)
                    .centerCrop()
                    .into(imageView)
            }
        }.addOnFailureListener {

        }
    }
}

fun downloadImg(img_url: String, context: Context, imageView: ImageView){
    Glide.with(context)
        .load(img_url)
        .centerCrop()
        .into(imageView);
}
