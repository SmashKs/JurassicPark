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

package taiwan.no.one.feat.dummy.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.kodein.di.instance
import taiwan.no.one.core.presentation.viewmodel.ResultLiveData
import taiwan.no.one.feat.dummy.data.entities.remote.DummyEntity
import taiwan.no.one.feat.dummy.domain.usecases.AddOrUpdateHistoryCase
import taiwan.no.one.feat.dummy.domain.usecases.AddOrUpdateHistoryReq
import taiwan.no.one.feat.dummy.domain.usecases.FetchDummyCase
import taiwan.no.one.feat.dummy.domain.usecases.FetchDummyReq
import taiwan.no.one.jurassicpark.core.viewmodel.BehindViewModel
import taiwan.no.one.ktx.livedata.toLiveData

internal class ResultViewModel : BehindViewModel() {
    private val fetchMusicCase by instance<FetchDummyCase>()
    private val addOrUpdateHistoryCase by instance<AddOrUpdateHistoryCase>()
    private val _dummies by lazy { ResultLiveData<List<DummyEntity>>() }
    val dummies = _dummies.toLiveData()
    private val _addOrUpdateResult by lazy { ResultLiveData<Boolean>() }
    val addOrUpdateResult = _addOrUpdateResult.toLiveData()

    fun search(keyword: String, page: Int = 0) = viewModelScope.launch {
        _dummies.value = fetchMusicCase.execute(FetchDummyReq(keyword, page))
    }

    fun add(keyword: String) = viewModelScope.launch {
        _addOrUpdateResult.value = addOrUpdateHistoryCase.execute(AddOrUpdateHistoryReq(keyword))
    }
}
