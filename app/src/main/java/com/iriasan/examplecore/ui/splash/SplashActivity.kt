package com.iriasan.examplecore.ui.splash

import android.content.Intent
import android.os.Handler
import com.cexmobility.corekotlin.ui.BaseActivity
import com.iriasan.examplecore.R

import com.iriasan.examplecore.ui.main.MainActivity
import com.iriasan.examplecore.utils.extensions.show
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private val handler = Handler()

    override val layoutResource: Int
        get() = R.layout.activity_splash

    override fun initializeView() {

        ivLogoSplash.show()

        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

    override fun hasInjection(): Boolean {
        return false
    }
}
