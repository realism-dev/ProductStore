package dev.realism.productstore.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.realism.productstore.core.data.repository.LocalDataSourceRepositoryImpl
import dev.realism.productstore.core.data.source.local.LocalDataSource
import dev.realism.productstore.core.data.source.local.RoomLocalDataSource
import dev.realism.productstore.core.data.source.local.db.ProductStoreRoomDatabase
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import javax.inject.Singleton

@Module
class LocalSourceModuleProvider {
    @Provides
    fun provideProductItemDao(database: ProductStoreRoomDatabase) = database.productItemDao()

    @Provides
    @Singleton
    fun providesLocalDatabase(context: Context): ProductStoreRoomDatabase {
        val dbFile = context.getDatabasePath("ip-test-task")
        val databaseBuilder = Room.databaseBuilder(
            context,
            ProductStoreRoomDatabase::class.java,
            "ip-test-task"
        ).fallbackToDestructiveMigration(false)

        if (!dbFile.exists()) {
            return databaseBuilder
                .createFromAsset("data.db")
                .build()
        }
        return databaseBuilder
            .build()
    }
}

@Module
abstract class LocalSourceModuleBinder {
    @Binds
    abstract fun bindRoomLocalDataSource(
        roomLocalDataSource: RoomLocalDataSource
    ): LocalDataSource

    @Binds
    abstract fun bindLocalDataSourceRepository(
        localDataSourceRepository: LocalDataSourceRepositoryImpl
    ): LocalDataSourceRepository
}