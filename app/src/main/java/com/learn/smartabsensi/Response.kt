package com.learn.smartabsensi

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Response(

	@field:SerializedName("news")
	val news: List<NewsItem?>? = null,

	@field:SerializedName("canteenMenus")
	val canteenMenus: List<CanteenMenusItem?>? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("attendance")
	val attendance: Attendance? = null
) : Parcelable

@Parcelize
data class NewsItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("name")
	val name: String? = null
) : Parcelable

@Parcelize
data class CanteenMenusItem(

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class Attendance(

	@field:SerializedName("buttonText")
	val buttonText: String? = null,

	@field:SerializedName("isInRange")
	val isInRange: Boolean? = null,

	@field:SerializedName("schoolName")
	val schoolName: String? = null
) : Parcelable
