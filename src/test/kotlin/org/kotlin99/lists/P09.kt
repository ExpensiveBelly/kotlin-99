package org.kotlin99.lists

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

tailrec fun <T> pack(list: List<T>, result: List<List<T>> = emptyList()): List<List<T>> =
    if (list.isEmpty()) result
    else {
        val packed = list.takeWhile { it == list.first() }
        pack(list.drop(packed.size), result + listOf(packed))
    }

class P09Test {
    @Test fun `pack consecutive duplicates of list elements into sublists`() {
        assertThat(pack("a".toList()), equalTo(listOf("a".toList())))
        assertThat(pack("aaa".toList()), equalTo(listOf("aaa".toList())))
        assertThat(pack("aaaabccaadeeee".toList()), equalTo(listOf(
            "aaaa".toList(), "b".toList(), "cc".toList(), "aa".toList(), "d".toList(), "eeee".toList()
        )))
    }
}