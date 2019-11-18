package com.iriasan.examplecore.ui.main

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iriasan.examplecore.R
import com.iriasan.examplecore.model.User
import com.iriasan.examplecore.utils.Constants.GenderUserValues.GENDER_MAN_USER
import com.iriasan.examplecore.utils.Constants.GenderUserValues.GENDER_WOMAN_USER
import kotlinx.android.synthetic.main.row_user.view.*


class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(user: User) {

        setNameUser(user)

        when {
            user.emailUser.isNotEmpty() -> itemView.tvEmailUser?.text = user.emailUser
        }

        setPhoneUser(user)
        setGenderUser(user, itemView.ivGender)
        setUserPicture(user)


    }

    private fun setNameUser(user: User) {

        var nameUser: String? = ""

        when {
            user.nameUser.firstName.isNotEmpty() -> nameUser = user.nameUser.firstName
        }

        when {
            user.nameUser.lastName.isNotEmpty() -> nameUser = nameUser + " " + user.nameUser.lastName
        }

        itemView.tvNameUser?.text = nameUser

    }

    private fun setPhoneUser(user: User) {

        var phoneUser: String? = ""

        when {
            user.phoneUser.isNotEmpty() -> phoneUser = user.phoneUser
        }

        when {
            user.cellUser.isNotEmpty() -> phoneUser = phoneUser + "/" + user.phoneUser
        }

        itemView.tvPhoneUser?.text = phoneUser

    }

    private fun setGenderUser(user: User, imageView: ImageView?) {

        when {
            user.genderUser.isNotEmpty() -> when (user.genderUser) {

                GENDER_WOMAN_USER -> imageView!!.setImageResource(R.drawable.ic_woman)
                GENDER_MAN_USER -> imageView!!.setImageResource(R.drawable.ic_man)
            }
        }

    }

    private fun setUserPicture(user: User) {

        when {
            user.pictureUser.thumbnailPicture.isNotEmpty() -> itemView.ivUser?.context?.let {
                Glide.with(
                    it
                ).load(user.pictureUser.thumbnailPicture).into(itemView.ivUser)
            }
            else -> setGenderUser(user, itemView.ivUser)
        }
    }
}
