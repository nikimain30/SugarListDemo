package com.sugar.cosmetics.view.activity

import android.view.View
import com.sugar.cosmetics.R
import com.sugar.cosmetics.activity.BaseActivity
import com.sugar.cosmetics.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {


    override val layoutId: Int
        get() = R.layout.activity_login

    override fun init() {
        setListener()
    }

    private fun setListener() {
        btnLogin.setOnClickListener {
            activityNavigator.navigateToHome(this)
        }
    }
}