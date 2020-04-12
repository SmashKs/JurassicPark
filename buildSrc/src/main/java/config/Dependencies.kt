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

package config

object Dependencies {
    val kotlinDeps = hashMapOf(
        "kotlin" to CoreDependency.KOTLIN,
        "reflect" to CoreDependency.KOTLIN_REFLECT,
        "coroutine" to CoreDependency.KOTLIN_COROUTINE
    )

    val kotlinAndroidDeps = kotlinDeps.apply {
        put("coroutineForAndroid", CoreDependency.ANDROID_COROUTINE)
    }

    private val commonAndroidDeps = hashMapOf(
        "appcompat" to LibraryDependency.JetPack.APPCOMPAT,
        "lifecycleService" to LibraryDependency.JetPack.LIFECYCLE_SERVICE,
        "lifecycleProcess" to LibraryDependency.JetPack.LIFECYCLE_PROCESS
    )
    private val commonKtxDeps = hashMapOf(
        "ktx" to LibraryDependency.AndroidKtx.KTX,
        "activityKtx" to LibraryDependency.AndroidKtx.ACTIVITY_KTX,
        "fragmentKtx" to LibraryDependency.AndroidKtx.FRAGMENT_KTX,
        "viewmodelKtx" to LibraryDependency.AndroidKtx.VIEWMODEL_KTX,
        "livedataKtx" to LibraryDependency.AndroidKtx.LIVEDATA_KTX,
        "runtimeKtx" to LibraryDependency.AndroidKtx.RUNTIME_KTX
    )

    val commonAndroidxDeps = commonAndroidDeps + commonKtxDeps

    val androidxKtxDeps = commonKtxDeps.apply {
        put("paletteKtx", LibraryDependency.AndroidKtx.PALETTE_KTX)
        put("collectionKtx", LibraryDependency.AndroidKtx.COLLECTION_KTX)
        put("navigationCommonKtx", LibraryDependency.AndroidKtx.NAVIGATION_COMMON_KTX)
        put("navigationFragmentKtx", LibraryDependency.AndroidKtx.NAVIGATION_FRAGMENT_KTX)
        put("navigationUiKtx", LibraryDependency.AndroidKtx.NAVIGATION_UI_KTX)
        put("workerKtx", LibraryDependency.AndroidKtx.WORKER_KTX)
//        "dynAnimKtx" to Deps.Presentation.dynAnimKtx
    }

    val androidxDeps = commonAndroidDeps.apply {
        put("materialDesign", LibraryDependency.JetPack.MATERIAL_DESIGN)
        put("recyclerview", LibraryDependency.JetPack.RECYCLERVIEW)
        put("cardview", LibraryDependency.JetPack.CARDVIEW)
        put("coordinatorLayout", LibraryDependency.JetPack.COORDINATOR_LAYOUT)
        put("constraintLayout", LibraryDependency.JetPack.CONSTRAINT_LAYOUT)
        put("annot", LibraryDependency.JetPack.ANNOT)
    }

    val diDeps = hashMapOf(
        "kodeinJvm" to LibraryDependency.Di.KODEIN_JVM,
        "kodeinCore" to LibraryDependency.Di.KODEIN_CORE,
        "kodeinAndroid" to LibraryDependency.Di.KODEIN_ANDROID_X
    )

    val localDeps = hashMapOf(
        "room" to LibraryDependency.Database.ROOM,
        "roomKtx" to LibraryDependency.Database.ROOM_KTX
    )

    val internetDeps = hashMapOf(
        "okhttp3" to LibraryDependency.Internet.OKHTTP,
        "okhttp_interceptor" to LibraryDependency.Internet.OKHTTP_INTERCEPTOR,
        "retrofit2" to LibraryDependency.Internet.RETROFIT2,
        "retrofit2_converter" to LibraryDependency.Internet.RETROFIT2_CONVERTER_GSON
    )

    val uiDeps = hashMapOf(
        "lottie" to LibraryDependency.Ui.LOTTIE,
        "adaptiveRecyclerView" to LibraryDependency.Jieyi.ARV,
        "quickDialog" to LibraryDependency.Jieyi.QUICK_DIALOG
    )

    val debugDeps = hashMapOf(
        "steho" to DebugLibraryDependency.STEHO,
        "stehoInterceptor" to DebugLibraryDependency.STEHO_INTERCEPTOR,
        "database" to DebugLibraryDependency.DEBUG_DB
//        "okhttpInterceptor" to DebugLibraryDependency.OK_HTTP_PROFILER
    )
}
