/*
 * MIT License
 *
 * Copyright (c) 2019 SmashKs
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

package taiwan.no.one.core.presentation.activity

import android.os.Bundle
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * The basic activity is for the normal activity that prepares all necessary variables or functions.
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    protected abstract val binding: ViewBinding

    //region Activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pre-set the view components.
        preSetContentView()
        setContentView(binding.root)
        // Post-set the view components.
        viewComponentBinding()
        componentListenersBinding()
        init(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        uninit()
        cancel() // cancel is extension on CoroutineScope
    }
    //endregion

    /**
     * Initialize doing some methods and actions.
     *
     * @param savedInstanceState previous state after this activity was destroyed.
     */
    @UiThread
    protected open fun init(savedInstanceState: Bundle?) = Unit

    /**
     * For separating the huge function code in [init]. Initialize all view components here before [setContentView].
     */
    @UiThread
    protected open fun preSetContentView() = Unit

    /**
     * For separating the huge function code in [init]. Initialize all view components here after [setContentView].
     */
    @UiThread
    protected open fun viewComponentBinding() = Unit

    /**
     * For separating the huge function code in [init]. Initialize all component listeners here.
     */
    @UiThread
    protected open fun componentListenersBinding() = Unit

    /**
     * Uninit the data or deconstruct objects.
     */
    @UiThread
    protected open fun uninit() = Unit
}