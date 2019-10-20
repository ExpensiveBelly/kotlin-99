package org.kotlin99.lists

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import org.kotlin99.common.tail

tailrec fun <T> reverse(list: List<T>, newList: List<T> = emptyList()): List<T> =
        when {
            list.isEmpty() -> emptyList()
            list.size == 1 -> listOf(list.first()) + newList
            else -> reverse(list.tail(), listOf(list.first()) + newList)
        }

class P05Test {
    @Test
    fun `reverse a list`() {
        assertThat(reverse(listOf<Int>()), equalTo(emptyList()))
        assertThat(reverse(listOf(1)), equalTo(listOf(1)))
        assertThat(reverse(listOf(1, 1, 2, 3, 5, 8)), equalTo(listOf(8, 5, 3, 2, 1, 1)))
    }
}