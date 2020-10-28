package org.kotlin99.lists

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

fun <T> rotate(n: Int, list: List<T>): List<T> =
    when {
        n == 0 -> list
        n > 0  -> list.drop(n) + list.take(n)
        else   -> list.takeLast(-n) + list.dropLast(-n)
    }

fun <T> rotateBySlicing(n: Int, list: List<T>): List<T> =
        if (n < 0) list.slice((list.size - (-n)) until list.size) + list.slice (0 until (list.size - (-n)))
        else list.slice(n until list.size) + list.slice(0 until n)

class P19Test {
    @Test
    fun `rotate a list N places to the left`() {
        assertThat(rotateBySlicing(3, "abcdefghijk".toList()), equalTo("defghijkabc".toList()))
        assertThat(rotateBySlicing(-2, "abcdefghijk".toList()), equalTo("jkabcdefghi".toList()))

        assertThat(rotate(3, "abcdefghijk".toList()), equalTo("defghijkabc".toList()))
        assertThat(rotate(-2, "abcdefghijk".toList()), equalTo("jkabcdefghi".toList()))
    }
}
