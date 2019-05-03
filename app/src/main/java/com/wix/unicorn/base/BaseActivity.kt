package com.wix.unicorn.base

import android.animation.TimeInterpolator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import org.koin.android.ext.android.inject
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.Gravity
import android.transition.Slide


abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int
    protected val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this, -1) {
            override fun createStartActivityOptions(command: Command?, activityIntent: Intent?): Bundle? {

                return null
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }



    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}