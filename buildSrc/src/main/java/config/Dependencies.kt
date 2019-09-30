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
    private val basicKotlin = hashMapOf(
        "kotlin" to Deps.Global.kotlin,
        "reflect" to Deps.Global.reflect
    )

    val kotlinAndroid = basicKotlin.apply {
        put("coroutineForAndroid", Deps.Presentation.androidCoroutine)
    }

    val kotlin = basicKotlin.apply {
        put("coroutine", Deps.Global.coroutine)
    }

    val commonAndroidx = hashMapOf(
        "appcompat" to Deps.Presentation.appcompat,
        "coordinatorLayout" to Deps.Presentation.coordinatorLayout,
        "lifecycle" to Deps.Presentation.lifecycle
    )

    val preziAndroidx = hashMapOf(
        "dexTool" to Deps.Presentation.dexTool,
        "materialDesign" to Deps.Presentation.materialDesign,
        "constraintLayout" to Deps.Presentation.constraintLayout,
        "recyclerview" to Deps.Presentation.recyclerview,
        "cardview" to Deps.Presentation.cardview,
        "annot" to Deps.Presentation.annot
    )

    val preziKtx = hashMapOf(
        "ktx" to Deps.Presentation.ktx,
        "fragmentKtx" to Deps.Presentation.fragmentKtx,
        "paletteKtx" to Deps.Presentation.paletteKtx,
        "collectionKtx" to Deps.Presentation.collectionKtx,
        "viewmodelKtx" to Deps.Presentation.viewmodelKtx,
        "livedataKtx" to Deps.Presentation.livedataKtx,
        "runtimeKtx" to Deps.Presentation.runtimeKtx,
        "navigationCommonKtx" to Deps.Presentation.navigationCommonKtx,
        "navigationFragmentKtx" to Deps.Presentation.navigationFragmentKtx,
        "navigationUiKtx" to Deps.Presentation.navigationUiKtx,
        "workerKtx" to Deps.Presentation.workerKtx
//        "dynAnimKtx" to Deps.Presentation.dynAnimKtx
    )

    val preziFirebaseQuality = hashMapOf(
        "analytics" to Deps.Presentation.firebaseAnalytics
//        "crashlytics" to Deps.Presentation.firebaseCrashlytics
    )

    private val kotlinDeps = hashMapOf(
        "kotlin" to CoreDependency.KOTLIN,
        "reflect" to CoreDependency.KOTLIN_REFLECT,
        "coroutine" to CoreDependency.KOTLIN_COROUTINE
    )

    val kotlinAndroidDeps = kotlinDeps.apply {
        put("coroutineForAndroid", LibraryDependency.ANDROID_COROUTINE)
    }

    private val commonAndroidDeps = hashMapOf(
        "appcompat" to LibraryDependency.APPCOMPAT,
        "lifecycle" to LibraryDependency.LIFECYCLE
    )

    private val commonKtxDeps = hashMapOf(
        "ktx" to LibraryDependency.KTX,
        "fragmentKtx" to LibraryDependency.FRAGMENT_KTX,
        "viewmodelKtx" to LibraryDependency.VIEWMODEL_KTX,
        "livedataKtx" to LibraryDependency.LIVEDATA_KTX,
        "runtimeKtx" to LibraryDependency.RUNTIME_KTX
    )

    val commonAndroidxDeps = commonAndroidDeps + commonKtxDeps

    val androidxKtxDeps = commonKtxDeps.apply {
        put("paletteKtx", LibraryDependency.PALETTE_KTX)
        put("collectionKtx", LibraryDependency.COLLECTION_KTX)
        put("navigationCommonKtx", LibraryDependency.NAVIGATION_COMMON_KTX)
        put("navigationFragmentKtx", LibraryDependency.NAVIGATION_FRAGMENT_KTX)
        put("navigationUiKtx", LibraryDependency.NAVIGATION_UI_KTX)
        put("workerKtx", LibraryDependency.WORKER_KTX)
//        "dynAnimKtx" to Deps.Presentation.dynAnimKtx
    }

    val androidxDeps = commonAndroidDeps.apply {
        put("dexTool", LibraryDependency.DEX_TOOL)
        put("lifecycle", LibraryDependency.LIFECYCLE)
        put("materialDesign", LibraryDependency.MATERIAL_DESIGN)
        put("recyclerview", LibraryDependency.RECYCLERVIEW)
        put("cardview", LibraryDependency.CARDVIEW)
        put("coordinatorLayout", LibraryDependency.COORDINATOR_LAYOUT)
        put("constraintLayout", LibraryDependency.CONSTRAINT_LAYOUT)
        put("annot", LibraryDependency.ANNOT)
    }

    val internetDeps = hashMapOf(
        "okhttp3" to LibraryDependency.OKHTTP,
        "retrofit2" to LibraryDependency.RETROFIT2,
        "retrofit2_converter" to LibraryDependency.RETROFIT2_CONVERTER_GSON
    )

    val uiDeps = hashMapOf(
        "lottie" to LibraryDependency.LOTTIE,
        "adaptiveRecyclerView" to LibraryDependency.ARV,
        "quickDialog" to LibraryDependency.QUICK_DIALOG
    )
}
