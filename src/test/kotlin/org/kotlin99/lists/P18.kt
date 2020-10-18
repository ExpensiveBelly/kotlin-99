package org.kotlin99.lists

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test
import org.kotlin99.common.tail

fun <T> slice(from: Int, to: Int, list: List<T>): List<T> =
    when {
        from > 0 -> slice(from - 1, to - 1, list.tail())
        to > 0 -> listOf(list.first()) + slice(from, to - 1, list.tail())
        else -> emptyList()
    }

@ExperimentalStdlibApi
fun <T> List<T>.slice(from: Int, to: Int) =
    DeepRecursiveFunction<Triple<List<T>, Int, Int>, List<T>> { (list, from, to) ->
        when {
            from > 0 -> callRecursive(Triple(list.tail(), from - 1, to - 1))
            to > 0 -> listOf(list.first()) + callRecursive(Triple(list.tail(), from, to - 1))
            else -> emptyList()
        }
    }(Triple(this, from, to))

@Suppress("unused")
fun <T> slice_(from: Int, to: Int, list: List<T>) = list.subList(from, to)

class P18Test {
    @Test
    fun `extract a slice from a list`() {
        assertThat(slice(3, 7, "abcdefghijk".toList()), equalTo("defg".toList()))
    }

    @ExperimentalStdlibApi
    @Test
    fun `extract a slice from a list deep recursive`() {
        assertThat("abcdefghijk".toList().slice(3, 7), equalTo("defg".toList()))
    }
}
