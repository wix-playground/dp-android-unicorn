package com.wix.unicorn

import com.wix.unicorn.utils.imageloader.GlideLoadDelegate
import com.wix.unicorn.utils.imageloader.ImageLoadDelegate
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

val presentationModule = module {
    single<ImageLoadDelegate> { GlideLoadDelegate() }
    single<Cicerone<Router>> { Cicerone.create() }
    single<Router> { provideRouter(get()) }
    single<NavigatorHolder> { provideNavigator(get()) }
}

private fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router
private fun provideNavigator(cicerone: Cicerone<Router>) = cicerone.navigatorHolder