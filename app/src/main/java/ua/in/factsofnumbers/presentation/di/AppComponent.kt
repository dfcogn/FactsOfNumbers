package ua.`in`.factsofnumbers.presentation.di

import dagger.Component
import ua.`in`.factsofnumbers.presentation.HomeFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetModule::class,
    RemoteDataSourceModule::class,
    RepositoryModule::class,
    ViewModelFactoryModule::class,
    LocalDataSourceModule::class,
    DatabaseModule::class
])
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
}