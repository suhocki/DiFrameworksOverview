package app.suhocki.diframeworksoverview.di.app

import android.content.ContentResolver
import android.content.Context
import app.suhocki.diframeworksoverview.di.login.LoginComponent
import dagger.Module
import dagger.Provides

@Module(
    includes = [SharedPreferencesModule::class, AppBindModule::class],
    subcomponents = [LoginComponent::class]
)
class AppModule {

    @Provides
    fun provideContentResolver(context: Context): ContentResolver {
        return context.contentResolver
    }
}