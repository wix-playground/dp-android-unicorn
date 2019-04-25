package com.wix.unicorn.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules

abstract class BaseActivity : AppCompatActivity() {
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        loadKoinModules(presentationModule)
    }
}