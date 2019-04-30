package com.wix.unicorn

import android.content.Context
import com.wix.unicorn.utils.imageloader.GlideLoadDelegate
import com.wix.unicorn.utils.imageloader.ImageLoadDelegate
import org.koin.dsl.module

val presentationModule = module {
    single { { context: Context -> context.getSharedPreferences("sample", Context.MODE_PRIVATE) }(get()) }
    single<ImageLoadDelegate> { GlideLoadDelegate() }

}
