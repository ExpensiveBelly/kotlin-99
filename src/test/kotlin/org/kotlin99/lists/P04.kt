package org.kotlin99.lists

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import org.kotlin99.common.tail

tailrec fun <T> length(list: List<T>, count: Int = 0): Int =
    if (list.isEmpty()) count else length(list.tail(), count + 1)

class P04Test {
    @Test fun `find the number of elements of a list`() {
        assertThat(length(listOf<Int>()), equalTo(0))
        assertThat(length(listOf(1, 1, 2, 3, 5, 8)), equalTo(6))
    }
}