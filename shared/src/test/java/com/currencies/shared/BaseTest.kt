package com.currencies.shared

import com.currencies.test_shared.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    // Overrides Dispatchers.Main used in Coroutines.
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher
        get() = coroutineRule.testDispatcher

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    inline fun testCoroutine(crossinline block: suspend TestCoroutineScope.() -> Unit) {
        testDispatcher.runBlockingTest {
            block(this)
        }
    }
}