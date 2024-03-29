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

package config

import org.gradle.kotlin.dsl.DependencyHandlerScope

const val DepEnvImpl = "implementation"
const val DepEnvApi = "api"
const val DepEnvDebugApi = "debugApi"
const val DepEnvKapt = "kapt"
const val DepEnvTest = "testImplementation"
const val DepEnvAndroidTest = "androidTestImplementation"

/**********************************************************
 * Below functions will be used in each Gradles directly. *
 **********************************************************/

fun DependencyHandlerScope.annotationDependencies() {
    DepEnvKapt(LibraryDependency.Database.ROOM_ANNOTATION)
    DepEnvKapt(LibraryDependency.JetPack.LIFECYCLE_COMPILER)
    DepEnvKapt(LibraryDependency.Tool.AUTO_SERVICE)
}

fun DependencyHandlerScope.coreDependencies() {
    kotlinAndroidDependencies(DepEnvApi)
    commonAndroidxDependencies(DepEnvApi)
    androidxUiDependencies(DepEnvApi)
    diDependencies(DepEnvApi)
    internetDependencies(DepEnvApi)
    localDependencies(DepEnvApi)
    // Others
    DepEnvApi(LibraryDependency.Tool.GSON)
}

fun DependencyHandlerScope.appDependencies() {
    androidxKtxDependencies(DepEnvApi)
    uiDependencies(DepEnvApi)
    firebaseDependencies(DepEnvApi)
    // Others
    DepEnvApi(LibraryDependency.Firebase.PLAY_CORE)
    DepEnvApi(LibraryDependency.Jieyi.KNIFER)
    DepEnvApi(LibraryDependency.Database.MMKV)
    DepEnvApi(LibraryDependency.Internet.COIL)
}

fun DependencyHandlerScope.ktxDependencies() {
    commonAndroidxDependencies(DepEnvImpl)
}

fun DependencyHandlerScope.widgetDependencies() {
    kotlinAndroidDependencies(DepEnvImpl)
    commonAndroidxDependencies(DepEnvImpl)
    // Auto Service
    DepEnvApi(LibraryDependency.Tool.AUTO_SERVICE)
    // Others
    DepEnvImpl(LibraryDependency.JetPack.RECYCLERVIEW)
    DepEnvImpl(LibraryDependency.JetPack.MATERIAL_DESIGN)
    DepEnvImpl(LibraryDependency.JetPack.CARDVIEW)
    DepEnvImpl(LibraryDependency.JetPack.CONSTRAINT_LAYOUT)
    DepEnvImpl(LibraryDependency.Ui.LOTTIE)
//    DepEnvImpl(Deps.Presentation.arv)
//    DepEnvImpl(Deps.Widget.quickDialog)
}

fun DependencyHandlerScope.mediaDependencies() {
    kotlinAndroidDependencies(DepEnvImpl)
    commonAndroidxDependencies(DepEnvImpl)
    DepEnvImpl(LibraryDependency.Media.EXOPLAYER_CORE)
}

fun DependencyHandlerScope.firebaseAuthDependencies() {
    DepEnvImpl(LibraryDependency.Firebase.FIREBASE_AUTH_GOOGLE)
    DepEnvImpl(LibraryDependency.Firebase.FIREBASE_AUTH_FACEBOOK)
}

fun DependencyHandlerScope.testDependencies() {
    kotlinDependencies(DepEnvImpl)
    DepEnvImpl(TestLibraryDependency.JUNIT)
    DepEnvImpl(TestLibraryDependency.COROUTINE)
    DepEnvImpl(TestLibraryDependency.ESPRESSO_CORE)

    DepEnvImpl(LibraryDependency.JetPack.MATERIAL_DESIGN)
}

/************************************
 * Below functions are for modeling *
 ************************************/

fun DependencyHandlerScope.kotlinDependencies(env: String) {
    env(CoreDependency.KOTLIN)
    env(CoreDependency.KOTLIN_REFLECT)
    env(CoreDependency.KOTLIN_COROUTINE)
}

fun DependencyHandlerScope.kotlinAndroidDependencies(env: String) {
    kotlinDependencies(env)
    env(CoreDependency.ANDROID_COROUTINE)
    env(CoreDependency.GOOGLE_PLAY_COROUTINE) // might only move the module need.
}

fun DependencyHandlerScope.androidJetpackDependencies(env: String) {
    env(LibraryDependency.JetPack.APPCOMPAT)
    env(LibraryDependency.JetPack.LIFECYCLE_SAVEDSTATE)
    env(LibraryDependency.JetPack.LIFECYCLE_SERVICE)
    env(LibraryDependency.JetPack.LIFECYCLE_PROCESS)
    env(LibraryDependency.JetPack.NAVIGATION_DYNAMIC_FEATURE)
}

fun DependencyHandlerScope.commonKtxDependencies(env: String) {
    env(LibraryDependency.AndroidKtx.KTX)
    env(LibraryDependency.AndroidKtx.ACTIVITY_KTX)
    env(LibraryDependency.AndroidKtx.FRAGMENT_KTX)
    env(LibraryDependency.AndroidKtx.VIEWMODEL_KTX)
    env(LibraryDependency.AndroidKtx.LIVEDATA_KTX)
    env(LibraryDependency.AndroidKtx.RUNTIME_KTX)
}

fun DependencyHandlerScope.androidxKtxDependencies(env: String) {
    commonKtxDependencies(env)
    env(LibraryDependency.AndroidKtx.PALETTE_KTX)
    env(LibraryDependency.AndroidKtx.COLLECTION_KTX)
    env(LibraryDependency.AndroidKtx.NAVIGATION_COMMON_KTX)
    env(LibraryDependency.AndroidKtx.NAVIGATION_FRAGMENT_KTX)
    env(LibraryDependency.AndroidKtx.NAVIGATION_UI_KTX)
    env(LibraryDependency.AndroidKtx.WORKER_KTX)
    env(LibraryDependency.JetPack.APP_STARTUP)
}

fun DependencyHandlerScope.commonAndroidxDependencies(env: String) {
    androidJetpackDependencies(env)
    commonKtxDependencies(env)
}

fun DependencyHandlerScope.androidxUiDependencies(env: String) {
    env(LibraryDependency.JetPack.MATERIAL_DESIGN)
    env(LibraryDependency.JetPack.RECYCLERVIEW)
    env(LibraryDependency.JetPack.CARDVIEW)
    env(LibraryDependency.JetPack.COORDINATOR_LAYOUT)
    env(LibraryDependency.JetPack.CONSTRAINT_LAYOUT)
    env(LibraryDependency.JetPack.ANNOT)
}

fun DependencyHandlerScope.diDependencies(env: String) {
    env(LibraryDependency.Di.KODEIN_ANDROID_X)
}

fun DependencyHandlerScope.internetDependencies(env: String) {
    env(LibraryDependency.Internet.OKHTTP)
    env(LibraryDependency.Internet.OKHTTP_INTERCEPTOR)
    env(LibraryDependency.Internet.RETROFIT2)
    env(LibraryDependency.Internet.RETROFIT2_CONVERTER_GSON)
}

fun DependencyHandlerScope.firebaseDependencies(env: String) {
    env(LibraryDependency.Firebase.FIREBASE_ANALYTICS)
    env(LibraryDependency.Firebase.FIREBASE_CRASHLYTICS)
    env(LibraryDependency.Firebase.FIREBASE_CONFIG)
    env(LibraryDependency.Firebase.FIREBASE_MESSAGING)
    env(LibraryDependency.Firebase.FIREBASE_DB)
    env(LibraryDependency.Firebase.FIREBASE_FIRESTORE)
//    env("com.google.guava:guava:30.0-jre") // For fixing firestore dependency error
    env(LibraryDependency.Firebase.FIREBASE_AUTH)
    env(LibraryDependency.Firebase.FIREBASE_AUTH_FACEBOOK)
}

fun DependencyHandlerScope.localDependencies(env: String) {
    env(LibraryDependency.Database.ROOM)
    env(LibraryDependency.Database.ROOM_KTX)
}

fun DependencyHandlerScope.uiDependencies(env: String) {
    env(LibraryDependency.Ui.LOTTIE)
    env(LibraryDependency.Jieyi.ARV)
    env(LibraryDependency.Jieyi.QUICK_DIALOG)
}

fun DependencyHandlerScope.debugDependencies(env: String) {
    env(DebugDependency.STEHO)
    env(DebugDependency.STEHO_INTERCEPTOR)
    env(DebugDependency.DEBUG_DB)
//    env(DebugDependency.OK_HTTP_PROFILER)
}

fun DependencyHandlerScope.unitTestDependencies(env: String = DepEnvTest) {
    env(TestLibraryDependency.JUNIT)
    env(TestLibraryDependency.COROUTINE)
    env(TestLibraryDependency.ASSERTK)
    env(TestLibraryDependency.MOCKK)
    env(TestLibraryDependency.OKHTTP_SERVER)
}

fun DependencyHandlerScope.androidTestDependencies(env: String = DepEnvAndroidTest) {
    env(TestLibraryDependency.ANDROID_JUNIT)
    env(TestLibraryDependency.ESPRESSO_CORE)
    env(TestLibraryDependency.ESPRESSO_INTENT)
    env(TestLibraryDependency.ESPRESSO_IDLING)
    env(TestLibraryDependency.CORE_KTX)
    env(TestLibraryDependency.ARCH_CORE)
    env(TestLibraryDependency.NAVIGATION)
    env(TestLibraryDependency.FRAGMENT)
    env(TestLibraryDependency.ASSERTK)
    env(TestLibraryDependency.MOCKK_ANDROID)
    env(TestLibraryDependency.ANDROID_JUNIT)
}
