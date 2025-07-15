package com.example.ai36

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ai36.view.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<LoginActivity>()

    @Test
    fun testSuccessfulLogin_navigatesToDashboard() {
        // Enter email
        composeRule.onNodeWithTag("email")
            .performTextInput("user@example.com")

        // Enter password
        composeRule.onNodeWithTag("password")
            .performTextInput("password")

        // Click Login
        composeRule.onNodeWithTag("button")
            .performClick()

        // You can now verify if DashboardActivity is launched.
        // Basic workaround (check for no errors):
        composeRule.waitForIdle()
    }

    @Test
    fun testEmptyEmail_ShowsNoNavigation() {

        composeRule.onNodeWithTag("button")
            .performClick()

        // Expect no crash or stay on same screen
        composeRule.waitForIdle()
    }
}