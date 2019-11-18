package com.iriasan.examplecore.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iriasan.examplecore.R
import com.iriasan.examplecore.model.User
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.row_user.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class UserAdapter(private val listUser: List<User>) : RecyclerView.Adapter<UserHolder>() {


    internal var clickListener: (User, CircleImageView) -> Unit= { _, _ -> }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return UserHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {

        holder.bindView(listUser[position])

        holder.itemView.cvUser.onClick {

            clickListener(listUser[holder.adapterPosition], holder.itemView.ivUser)
        }

    }

    override fun getItemCount(): Int {
        return listUser.size
    }


}
