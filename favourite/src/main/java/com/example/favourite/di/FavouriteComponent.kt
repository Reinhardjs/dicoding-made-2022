package com.example.favourite.di

import android.content.Context
import com.example.dicoding_made_2022.di.FavouriteModuleDependencies
import com.example.favourite.fragments.favourite.FavouriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavouriteModuleDependencies::class])
interface FavouriteComponent {

    fun inject(fragment: FavouriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favouriteModuleDependencies: FavouriteModuleDependencies): Builder
        fun build(): FavouriteComponent
    }

}