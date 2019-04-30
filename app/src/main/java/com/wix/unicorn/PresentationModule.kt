package com.wix.unicorn

import com.wix.unicorn.utils.imageloader.GlideLoadDelegate
import com.wix.unicorn.utils.imageloader.ImageLoadDelegate
import org.koin.dsl.module

val presentationModule = module { single<ImageLoadDelegate> { GlideLoadDelegate() } }
