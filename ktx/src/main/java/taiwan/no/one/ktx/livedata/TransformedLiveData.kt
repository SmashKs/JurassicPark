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

package taiwan.no.one.ktx.livedata

import androidx.annotation.WorkerThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.devrapid.kotlinshaver.accessible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import taiwan.no.one.ktx.BuildConfig

abstract class TransformedLiveData<S, T> : LiveData<T>(), Observer<S>, SilentHook<T> {
    protected abstract val source: LiveData<S>

    //region The Code comes from [SilentLiveData].
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        try {
            beSilent(observer)
        }
        catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
    }

    override fun beSilent(observer: Observer<in T>) {
        // Get wrapper's version.
        val classLiveData = LiveData::class.java
        val fieldObservers = classLiveData.getDeclaredField("mObservers").accessible()
        val objectObservers = fieldObservers.get(this)
        val classObservers = objectObservers.javaClass
        val methodGet = classObservers.getDeclaredMethod("get", Any::class.java).accessible()
        val objectWrapperEntry = methodGet.invoke(objectObservers, observer)
        lateinit var objectWrapper: Any
        if (objectWrapperEntry is Map.Entry<*, *>) {
            objectWrapper = objectWrapperEntry.value ?: throw NullPointerException()
        }
        val classObserverWrapper = objectWrapper.javaClass.superclass
        val fieldLastVersion =
            (classObserverWrapper?.getDeclaredField("mLastVersion") ?: throw NullPointerException()).accessible()
        // Get LiveData's version.
        val fieldVersion = classLiveData.getDeclaredField("mVersion").accessible()
        val objectVersion = fieldVersion.get(this)
        // Set wrapper's version.
        fieldLastVersion.set(objectWrapper, objectVersion)
    }
    //endregion

    override fun onActive() = source.observeForever(this)

    override fun onInactive() = source.removeObserver(this)

    /**
     * The processing of the data changed or transformed.
     * NOTE: If the [getTransformed] returns `null`, the data won't be changed; otherwise, change data.
     *
     * @param source
     */
    override fun onChanged(source: S) {
        CoroutineScope(Dispatchers.IO).launch { getTransformed(source)?.let(this@TransformedLiveData::postValue) }
    }

    @WorkerThread
    protected abstract fun getTransformed(source: S): T?
}
