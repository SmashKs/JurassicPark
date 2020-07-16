/*
 * MIT License
 *
 * Copyright (c) 2020 SmashKs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package taiwan.no.one.feat.dummy.data

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit
import taiwan.no.one.core.data.remote.DefaultRetrofitConfig
import taiwan.no.one.feat.dummy.FeatModules.Constant.FEAT_NAME
import taiwan.no.one.feat.dummy.data.contracts.DataStore
import taiwan.no.one.feat.dummy.data.local.configs.DummyDatabase
import taiwan.no.one.feat.dummy.data.local.services.database.v1.SearchHistoryDao
import taiwan.no.one.feat.dummy.data.remote.RestfulApiFactory
import taiwan.no.one.feat.dummy.data.remote.configs.DummyConfig
import taiwan.no.one.feat.dummy.data.remote.services.retrofit.v1.DummyService
import taiwan.no.one.feat.dummy.data.repositories.DummyRepository
import taiwan.no.one.feat.dummy.data.repositories.HistoryRepository
import taiwan.no.one.feat.dummy.data.stores.LocalStore
import taiwan.no.one.feat.dummy.data.stores.RemoteStore
import taiwan.no.one.feat.dummy.domain.repositories.DummyRepo
import taiwan.no.one.feat.dummy.domain.repositories.HistoryRepo
import taiwan.no.one.jurassicpark.di.Constant
import taiwan.no.one.jurassicpark.provider.ModuleProvider

internal object DataModules : ModuleProvider {
    private const val TAG_LOCAL_DATA_STORE = "$FEAT_NAME local data store"
    private const val TAG_REMOTE_DATA_STORE = "$FEAT_NAME remote data store"

    override fun provide(context: Context) = DI.Module("${FEAT_NAME}DataModule") {
        import(localProvide())
        import(remoteProvide(context))

        bind<DataStore>(TAG_LOCAL_DATA_STORE) with singleton { LocalStore(instance(), instance(), instance()) }
        bind<DataStore>(TAG_REMOTE_DATA_STORE) with singleton { RemoteStore(instance()) }

        bind<DummyRepo>() with singleton {
            DummyRepository(instance(TAG_REMOTE_DATA_STORE), instance(TAG_LOCAL_DATA_STORE))
        }
        bind<HistoryRepo>() with singleton { HistoryRepository(instance(TAG_LOCAL_DATA_STORE)) }
    }

    private fun localProvide() = DI.Module("${FEAT_NAME}LocalModule") {
        bind<DummyDatabase>() with singleton { DummyDatabase.getDatabase(instance()) }

        bind<SearchHistoryDao>() with singleton { instance<DummyDatabase>().createSearchHistoryDao() }
    }

    private fun remoteProvide(context: Context) = DI.Module("${FEAT_NAME}RemoteModule") {
        bind<DummyConfig>() with instance(RestfulApiFactory().createDummyConfig())

        bind<Retrofit>(Constant.TAG_FEAT_DUMMY_RETROFIT) with singleton {
            DefaultRetrofitConfig(context, instance<DummyConfig>().apiBaseUrl).provideRetrofitBuilder().build()
        }

        bind<DummyService>() with singleton {
            instance<Retrofit>(Constant.TAG_FEAT_DUMMY_RETROFIT).create(DummyService::class.java)
        }
    }
}
