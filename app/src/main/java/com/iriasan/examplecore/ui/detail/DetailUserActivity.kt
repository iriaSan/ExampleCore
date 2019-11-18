package com.iriasan.examplecore.ui.detail

import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.cexmobility.corekotlin.ui.BaseActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.iriasan.examplecore.R
import com.iriasan.examplecore.model.User
import com.iriasan.examplecore.utils.Constants.GenderUserValues.GENDER_MAN_USER
import com.iriasan.examplecore.utils.Constants.GenderUserValues.GENDER_WOMAN_USER
import com.iriasan.examplecore.utils.Constants.IntentValues.INTENT_USER_DETAIL_DATA
import com.iriasan.examplecore.utils.extensions.hide
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.layout_phone_user_detail.*

class DetailUserActivity : BaseActivity() {


    private var menuItem: MenuItem? = null
    private var gender: String? = null

    override val layoutResource: Int
        get() = R.layout.activity_detail_user


    override fun initializeView() {

        when {
            intent.hasExtra(INTENT_USER_DETAIL_DATA) -> {

                intent.getParcelableExtra<User>(INTENT_USER_DETAIL_DATA)?.let { itUser ->
                    setUpViews(itUser)
                }

                setUpToolbar()
            }
        }

    }

    override fun hasInjection() = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        this.menuItem = menu.findItem(R.id.action_info)
        menuItem?.isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fab.visibility = GONE
    }

    private fun setUpViews(user: User) {

        setUserImage(user)
        setNameUserDetail(user)

        when {
            user.emailUser.isNotEmpty() -> tvEmailUserDetail!!.text = user.emailUser
        }

        when {
            user.genderUser.isNotEmpty() -> {
                this.gender = user.genderUser
                tvGenderUserDetail?.text = user.genderUser
            }
        }

        setFloatingActionButton()

        setUserPhoneDetail(user)
        setUserLocationDetail(user)

        when {
            user.registeredUser.dateRegister.isNotEmpty() -> tvRegisterDateUserDetail?.text =
                user.registeredUser.dateRegister
        }

    }

    private fun setUserImage(user: User) {

        when {
            user.pictureUser.largePicture.isNotEmpty() -> expandedImage?.context?.let {
                Glide.with(it).load(user.pictureUser.largePicture).into(expandedImage)
            }
        }
    }

    private fun setUserPhoneDetail(user: User) {

        when {
            user.phoneUser.isNotEmpty() -> tvPhoneUserDetail?.text = user.phoneUser
            else -> {
                tvPhoneUserDetail?.hide()
                tvLabelPhoneUserDetail?.hide()
            }
        }

        when {
            user.cellUser.isNotEmpty() -> tvMobilePhoneUserDetail?.text = user.cellUser
        }
    }

    private fun setNameUserDetail(user: User) {

        var userName: String? = ""

        when {
            user.nameUser.firstName.isNotEmpty() -> userName =
                user.nameUser.firstName
        }

        when {
            user.nameUser.lastName.isNotEmpty() -> userName =
                userName + " " + user.nameUser.lastName
        }

        collapsingToolbar?.title = userName
        collapsingToolbar?.setCollapsedTitleTextColor(
            ContextCompat.getColor(
                this,
                R.color.colorAccent
            )
        )
        collapsingToolbar?.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorAccent))
    }

    private fun setUserLocationDetail(user: User) {

        var locationUser: String? = ""
        user.locationUser?.let { itLocationUser ->

            itLocationUser.streetLocation.let {
                when {
                    !user.locationUser?.streetLocation?.nameStreet.isNullOrEmpty() -> locationUser =
                        user.locationUser!!.streetLocation.nameStreet
                }

                when {
                    user.locationUser?.streetLocation?.numberStreet != null -> locationUser =
                        locationUser + " " + user.locationUser!!.streetLocation.numberStreet.toString()
                }
            }

            locationUser = itLocationUser.cityLocation.let {
                locationUser + ", " + user.locationUser?.cityLocation
            }

            locationUser = itLocationUser.stateLocation.let {

                locationUser + ", " + user.locationUser?.stateLocation
            }
        }

        tvLocationUserDetail?.text = locationUser

    }

    private fun setUpToolbar() {

        toolbarDetail?.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(toolbarDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        collapsingToolbarListener()
    }

    private fun collapsingToolbarListener() {

        appBarLayout?.addOnOffsetChangedListener(object : OnOffsetChangedListener {

            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                when (scrollRange) {
                    -1 -> scrollRange = appBarLayout.totalScrollRange
                }
                when {
                    scrollRange + verticalOffset == 0 -> {
                        isShow = true

                        when (gender) {
                            GENDER_MAN_USER -> menuItem?.setIcon(R.drawable.ic_man_dark)
                            GENDER_WOMAN_USER -> menuItem?.setIcon(R.drawable.ic_woman_dark)
                        }

                        menuItem?.isVisible = true

                    }
                    isShow -> {
                        isShow = false
                        menuItem?.isVisible = false
                    }
                }
            }
        })
    }

    private fun setFloatingActionButton() {

        when (gender) {
            GENDER_MAN_USER -> fab?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_man
                )
            )
            GENDER_WOMAN_USER -> fab?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_woman
                )
            )
        }
    }


}
