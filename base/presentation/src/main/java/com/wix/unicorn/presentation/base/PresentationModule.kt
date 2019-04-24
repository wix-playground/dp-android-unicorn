package com.wix.unicorn.presentation.base

import com.wix.unicorn.presentation.base.imageloader.GlideLoadDelegate
import com.wix.unicorn.presentation.base.imageloader.ImageLoadDelegate
import org.koin.dsl.module

val presentationModule = module { single<ImageLoadDelegate> { GlideLoadDelegate() } }