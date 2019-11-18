package com.iriasan.examplecore.ui.main

import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cexmobility.corekotlin.data.general.Resource
import com.cexmobility.corekotlin.ui.BaseActivity
import com.iriasan.examplecore.R
import com.iriasan.examplecore.model.User
import com.iriasan.examplecore.ui.detail.DetailUserActivity
import com.iriasan.woomtest.ui.main.MainViewModel
import com.iriasan.examplecore.utils.Constants.IntentValues.INTENT_USER_DETAIL_DATA
import com.iriasan.examplecore.utils.extensions.hide
import com.iriasan.examplecore.utils.extensions.show
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mainViewModel: MainViewModel? = null
    private var animationShown = false
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var manager: LinearLayoutManager? = null
    private var listUsers: MutableList<User>? = null
    private var userAdapter: UserAdapter? = null
    private var loading = true
    private var recyclerViewState: Parcelable? = null


    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun initializeView() {

        mainViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModelImpl::class.java)

        getUserList(false)


    }

    override fun hasInjection(): Boolean = true

    private fun getUserList(isBottomScroll: Boolean) {

        mainViewModel?.getAllUsers(10)?.observe(this, Observer { itResponse ->
            when (itResponse.status) {

                Resource.Status.ERROR -> getListUsersFromResponse(
                    response = itResponse,
                    isBottomScroll = isBottomScroll
                )
                Resource.Status.LOADING -> pbLoadingUserData.show()
                Resource.Status.SUCCESS -> getListUsersFromResponse(
                    response = itResponse,
                    isBottomScroll = isBottomScroll
                )

            }
        })

    }


    private fun getListUsersFromResponse(response: Resource<List<User>>, isBottomScroll: Boolean) {

        pbLoadingUserData.hide()

        when {
            response.data != null && response.data!!.isNotEmpty() -> {
                loading = true
                listUsers = response.data!!.toMutableList()
                checkIfIsFirsTimeToGetUsers(isBottomScroll, response.data)
            }
            else -> {
                tvNoUsers?.show()
                rvUsers?.hide()
            }
        }
    }


    private fun checkIfIsFirsTimeToGetUsers(isBottomScroll: Boolean, listNewUsers: List<User>?) {
        when {
            isBottomScroll -> addMoreUsersToList(listNewUsers)
            else -> setUpUserList()
        }
    }

    private fun setUpUserList() {

        tvNoUsers?.hide()
        rvUsers?.show()

        manager = LinearLayoutManager(this)
        rvUsers?.layoutManager = manager

        userAdapter = listUsers?.toList()?.let { UserAdapter(it) }
        rvUsers?.adapter = userAdapter

        runLayoutAnimation()
        knowRecyclerIsInLastPosition()

        userAdapter?.clickListener = { itUser, itImageView ->
            loadNext(itImageView, itUser)
        }

    }

    fun loadNext(view: View, user: User) {
        val activityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, "imageMain")
        val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
        intent.putExtra(INTENT_USER_DETAIL_DATA, user)
        startActivity(intent, activityOptionsCompat.toBundle())
    }

    private fun runLayoutAnimation() {

        when {
            !animationShown -> {
                val context = rvUsers!!.context
                val controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
                rvUsers?.layoutAnimation = controller
                rvUsers?.adapter!!.notifyDataSetChanged()
                rvUsers?.scheduleLayoutAnimation()
                animationShown = true
            }
        }
    }

    private fun knowRecyclerIsInLastPosition() {


        rvUsers?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                when {
                    dy > 0 -> {

                        manager?.childCount?.let { visibleItemCount = it }
                        manager?.itemCount?.let { totalItemCount = it }
                        manager?.findFirstVisibleItemPosition()?.let { pastVisiblesItems = it }


                        when {
                            loading -> when {
                                visibleItemCount + pastVisiblesItems >= totalItemCount -> {
                                    recyclerViewState =
                                        recyclerView.layoutManager?.onSaveInstanceState()
                                    getUserList(true)
                                    loading = false
                                }
                            }
                        }

                    }
                }
            }
        })

    }

    private fun addMoreUsersToList(listNewUsers: List<User>?) {
        listUsers?.addAll(listNewUsers!!)
        userAdapter?.notifyDataSetChanged()
        rvUsers.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

}
