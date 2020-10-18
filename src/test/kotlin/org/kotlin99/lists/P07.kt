package org.kotlin99.lists

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

@Suppress("UNCHECKED_CAST")
tailrec fun flatten(list: List<Any>): List<Any> {
    if (list.all { it !is List<*> }) return list

    val ret = mutableListOf<Any?>()
    list.forEach { any ->
        if (any is List<*>) ret.addAll(any)
        else ret.add(any)
    }

    return flatten(ret as List<Any>)
}

@ExperimentalStdlibApi
@Suppress("UNCHECKED_CAST")
fun <T> List<T>.flattenDeepRecursive() = DeepRecursiveFunction<List<T>, List<T>> { list ->
    if (list.all { it !is List<*> }) list
    else {
        val ret = mutableListOf<Any?>()
        list.forEach { t: T ->
            if (t is List<*>) ret.addAll(t)
            else ret.add(t)
        }
        callRecursive(ret as List<T>)
    }
}(this)


class P07Test {
    @Test
    fun `flatten a nested list structure`() {
        assertThat(flatten(listOf(1)), equalTo(listOf<Any>(1)))
        assertThat(flatten(listOf(1, listOf(2))), equalTo(listOf<Any>(1, 2)))
        assertThat(
            flatten(listOf(listOf(1, 1), 2, listOf(3, listOf(5, 8)))),
            equalTo(listOf<Any>(1, 1, 2, 3, 5, 8))
        )
    }
}