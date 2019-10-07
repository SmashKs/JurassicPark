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

package taiwan.no.one.jurassicpark

import android.content.Context
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import taiwan.no.one.core.presentation.fragment.BaseFragment
import taiwan.no.one.jurassicpark.databinding.ActivitySecondBinding
import taiwan.no.one.jurassicpark.viewmodel.MainViewModel
import taiwan.no.one.jurassicpark.viewmodel.VMProvider

class MainFragment : BaseFragment<MainActivity, ActivitySecondBinding>() {
    val manager by lazy { SplitInstallManagerFactory.create(requireContext()) }
    private val request by lazy { SplitInstallRequest.newBuilder().addModule("featDummy").build() }
    private val vm by viewModels<MainViewModel> { VMProvider }
    private val listener by lazy {
        SplitInstallStateUpdatedListener {
            when (it.status()) {
                SplitInstallSessionStatus.INSTALLED -> {
                    val route =
                        Class.forName("taiwan.no.one.dummy.FeatureARoute").kotlin.objectInstance as? NavigationGraphRoute
                    addNavGraphDestination(route!!, findNavController(), requireContext())
//                    launch {
//                        delay(1500)
//                        uiSwitch {
//                            findNavController().navigate(Uri.parse("https://taiwan.no.one.dummy/activity"))
//                        }
//                    }
                }
            }
        }
    }

    /**
     * For separating the huge function code in [rendered]. Initialize all view components here.
     */
    override fun viewComponentBinding() {
        binding.tvMsg.text = vm.test.toString()
    }

    /**
     * For separating the huge function code in [rendered]. Initialize all component listeners here.
     */
    override fun componentListenersBinding() {
        binding.btnClick.setOnClickListener {
            //            findNavController().navigate(MainFragmentDirections.actionFragmentSecondToActivitySecond(13))
            findNavController().navigate(Uri.parse("https://taiwan.no.one.dummy/dummy"))
        }
        manager.startInstall(request)
    }

    override fun onResume() {
        super.onResume()
        manager.registerListener(listener)
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(listener)
    }

    private fun addNavGraphDestination(
        navigationGraphRoute: NavigationGraphRoute,
        navController: NavController,
        context: Context
    ): NavGraph {
        val navigationId = context.resources.getIdentifier(navigationGraphRoute.graphName,
                                                           "navigation",
                                                           navigationGraphRoute.packageName)
        println("===========ididid======================================")
        println(navigationId)
        println("===========ididid======================================")
        val newGraph = navController.navInflater.inflate(navigationGraphRoute.resourceId)
        navController.graph.addDestination(newGraph)
        return newGraph
    }
}

/**
Provides necessary information for NavGraph in other modules
 */
interface NavigationGraphRoute {
    /*
    The inflated NavGraph
    */
    var navGraph: NavGraph
    /**
    The .xml name for the nav-graph
     */
    val graphName: String
    /**
    The full package name where the nav-graph is located
     */
    val packageName: String

    val resourceId: Int
}
