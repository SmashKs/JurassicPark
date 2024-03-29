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

package taiwan.no.one.core.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import taiwan.no.one.core.exceptions.internet.InternetException.ParameterNotMatchException
import taiwan.no.one.ext.DEFAULT_STR

/**
 * A base abstract class for wrapping a coroutine [OneShotUsecase] object and do the
 * error handling when an error or cancellation happened.
 */
abstract class OneShotUsecase<T : Any, R : Usecase.RequestValues> : Usecase<R> {
    protected abstract suspend fun acquireCase(parameter: R? = null): T

    open suspend fun execute(parameter: R? = null) = withContext(Dispatchers.Default) {
        runCatching { acquireCase(parameter) }
    }

    protected suspend fun R?.ensure(errorMessage: String = DEFAULT_STR, block: suspend R.() -> T) =
        this?.run { block() } ?: throw ParameterNotMatchException(errorMessage)
}
