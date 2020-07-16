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

package taiwan.no.one.feat.dummy.data.stores

import taiwan.no.one.ext.exceptions.UnsupportedOperation
import taiwan.no.one.feat.dummy.data.contracts.DataStore
import taiwan.no.one.feat.dummy.data.entities.local.SearchHistoryEntity
import taiwan.no.one.feat.dummy.data.entities.remote.DummyEntity
import taiwan.no.one.feat.dummy.data.remote.parameters.DummyBank
import taiwan.no.one.feat.dummy.data.remote.services.retrofit.v1.DummyService

/**
 * The implementation of the remote data store. The responsibility is selecting a correct
 * remote service to access the data.
 */
internal class RemoteStore(
    private val dummyService: DummyService
) : DataStore {
    override suspend fun getDummy(keyword: String, page: Int): List<DummyEntity> {
        val queries = hashMapOf(
            DummyBank.PARAM_NAME_QUERY to keyword,
            DummyBank.PARAM_NAME_PAGE_NO to page.toString(),
        )
        return dummyService.retrieveDummy(queries)
    }

    override suspend fun createDummy(keyword: String, page: Int, dummy: List<DummyEntity>) = UnsupportedOperation()

    override fun getSearchHistories(count: Int) = UnsupportedOperation()

    override suspend fun createOrModifySearchHistory(keyword: String) = UnsupportedOperation()

    override suspend fun removeSearchHistory(keyword: String?, entity: SearchHistoryEntity?) = UnsupportedOperation()
}
