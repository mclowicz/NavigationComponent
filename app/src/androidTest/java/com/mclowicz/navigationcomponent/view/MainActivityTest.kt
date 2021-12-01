package com.mclowicz.navigationcomponent.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.mclowicz.navigationcomponent.MainActivity
import com.mclowicz.navigationcomponent.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var navController: NavController

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        scenario.onActivity { activity ->
            navController.setGraph(R.navigation.navigation_graph)
            Navigation.setViewNavController(
                activity.findViewById(R.id.my_nav_host_fragment),
                navController
            )
        }
    }

    @Test
    fun initialDestinationIsHomeFragment() {
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.destination_home)
    }

    @Test
    fun homeFragmentToSettingFragmentDirection() {
        onView(withContentDescription(R.string.fragment_title_settings)).perform(click())
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.destination_settings)
    }

    @Test
    fun onHamburgerMenuCLickedOpenDrawerPanel() {
        onView(withContentDescription(R.string.nav_app_bar_open_drawer_description)).perform(click())
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun onDrawerMenuItemClickedNavigateToListShowBottomNav() {
        onView(withContentDescription(R.string.nav_app_bar_open_drawer_description)).perform(click())
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.drawer_view)).perform(NavigationViewActions.navigateTo(R.id.destination_list))
        onView(withId(R.id.bottom_nav_view)).check(matches(isDisplayed()))
    }
}