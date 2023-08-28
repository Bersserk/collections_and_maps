package com.example.collections_and_maps.models.benchmarks

import com.example.collections_and_maps.R
import java.util.Collections
import java.util.LinkedList
import java.util.Random
import java.util.concurrent.CopyOnWriteArrayList

open class CollectionsBenchmark : Benchmark {

    private val random = Random()
    private val listNamesForHead =
        intArrayOf(R.string.ArrayList, R.string.LinkedList, R.string.CopyOnWrite)
    private val listNamesForMethod = intArrayOf(
        R.string.add_begin, R.string.add_middle,
        R.string.add_end, R.string.search_value, R.string.remove_begin,
        R.string.remove_middle, R.string.remove_end
    )

    override fun getItemsList(showProgress: Boolean): List<ResultItem> {
        val itemsList: MutableList<ResultItem> = ArrayList()
        for (head in listNamesForHead) {
            itemsList.add(ResultItem(head, R.string.empty, ResultItem.EMPTY, false))
        }
        for (methodsID in listNamesForMethod) {
            itemsList.add(ResultItem(R.string.empty, methodsID, ResultItem.EMPTY, false))
            for (headsID in listNamesForHead) {
                itemsList.add(ResultItem(headsID, methodsID, ResultItem.EMPTY, showProgress))
            }
        }
        return itemsList
    }

    override fun getMeasureTime(rItem: ResultItem, value: Int): Double {
        val list: MutableList<Int> = when (rItem.headerText) {
            R.string.ArrayList -> ArrayList(Collections.nCopies(value, 0))
            R.string.LinkedList -> LinkedList(Collections.nCopies(value, 0))
            R.string.CopyOnWrite -> CopyOnWriteArrayList(Collections.nCopies(value, 0))
            else -> throw IllegalStateException("Unexpected value: " + rItem.headerText)
        }
        return calculateResult(rItem.methodName, list)
    }

    override fun getSpan(): Int = listNamesForHead.size



    private fun calculateResult(methodName: Int, list: MutableList<Int>): Double {
        return when (methodName) {
            R.string.add_begin -> addItemToStart(list)
            R.string.add_middle -> addItemToMiddle(list)
            R.string.add_end -> addItemToEnd(list)
            R.string.search_value -> searchByValue(list)
            R.string.remove_begin -> removingInBeginning(list)
            R.string.remove_middle -> removingInMiddle(list)
            R.string.remove_end -> removingInEnd(list)
            else -> throw IllegalArgumentException()
        }
    }

    private fun addItemToStart(list: MutableList<Int>): Double {
        val start = System.nanoTime().toDouble()
        list.add(0)
        return System.nanoTime() - start
    }

    private fun addItemToMiddle(list: MutableList<Int>): Double {
        val start = System.nanoTime().toDouble()
        list.add(list.size / 2, 0)
        return System.nanoTime() - start
    }

    private fun addItemToEnd(list: MutableList<Int>): Double {
        val start = System.nanoTime().toDouble()
        list.add(list.size, 0)
        return System.nanoTime() - start
    }

    private fun searchByValue(list: MutableList<Int>): Double {
        val index = random.nextInt(list.size)
        list.add(index, index)
        val start = System.nanoTime().toDouble()
        val has = list.contains(index)
        return System.nanoTime() - start
    }

    private fun removingInBeginning(list: MutableList<Int>): Double {
        val start = System.nanoTime().toDouble()
        list.removeAt(0)
        return System.nanoTime() - start
    }

    private fun removingInMiddle(list: MutableList<Int>): Double {
        val start = System.nanoTime().toDouble()
        list.removeAt(list.size / 2)
        return System.nanoTime() - start
    }

    private fun removingInEnd(list: MutableList<Int>): Double {
        val start = System.nanoTime().toDouble()
        list.removeAt(list.size - 1)
        return System.nanoTime() - start
    }
}
