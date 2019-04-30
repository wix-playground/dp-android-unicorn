package com.wix.unicorn.feature.splash

import com.wix.unicorn.utils.imageloader.GlideLoadDelegate
import org.koin.core.qualifier.named
import org.koin.dsl.module

val splashModule = module {
    scope(named("Splash")) { GlideLoadDelegate() }
}