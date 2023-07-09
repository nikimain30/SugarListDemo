package com.sugar.cosmetics.navigator

import android.app.Activity
import com.sugar.cosmetics.view.activity.HomeScreenActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityNavigator @Inject constructor(){
    fun navigateToHome (activity: Activity) {
        activity.startActivity(HomeScreenActivity.getCallingIntent(activity))
    }
}