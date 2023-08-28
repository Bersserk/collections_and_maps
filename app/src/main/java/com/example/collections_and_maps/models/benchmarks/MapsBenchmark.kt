package com.example.collections_and_maps.models.benchmarks

import com.example.collections_and_maps.R
import java.util.TreeMap

open class MapsBenchmark : Benchmark {
    private val listNamesForHead = intArrayOf(R.string.HashMap, R.string.TreeMap)
    private val listNamesForMethod =
        intArrayOf(R.string.add_new, R.string.search_key, R.string.removing)

    override fun getItemsList(showProgress: Boolean): List<ResultItem> {
        val itemsList: MutableList<ResultItem> = ArrayList()
        for (itemOfListHead in listNamesForHead) {
            itemsList.add(
                ResultItem(
                    itemOfListHead,
                    R.string.empty,
                    ResultItem.EMPTY,
                    false
                )
            )
        }
        for (methodsID in listNamesForMethod) {
            itemsList.add(ResultItem(R.string.empty, methodsID, ResultItem.EMPTY, false))
            for (headsID in listNamesForHead) {
                itemsList.add(
                    ResultItem(
                        headsID,
                        methodsID,
                        ResultItem.EMPTY,
                        showProgress
                    )
                )
            }
        }
        return itemsList
    }

    override fun getMeasureTime(rItem: ResultItem, value: Int): Double {
        check(value >= 0) { "Unexpected value: $value" }
        val map: MutableMap<Int, Int?>
        map = if (rItem.headerText == R.string.HashMap) {
            createMap(HashMap(), value)
        } else if (rItem.headerText == R.string.TreeMap) {
            createMap(TreeMap(), value)
        } else {
            throw IllegalStateException("Unexpected value: " + rItem.headerText)
        }
        return calculateResult(rItem.methodName, map)
    }

    override fun getSpan(): Int {
        return listNamesForHead.size
    }


    private fun calculateResult(methodName: Int, map: MutableMap<Int, Int?>): Double {
        return if (methodName == R.string.add_new) {
            addingNew(map)
        } else if (methodName == R.string.search_key) {
            searchByKey(map)
        } else if (methodName == R.string.removing) {
            removing(map)
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun addingNew(map: MutableMap<Int, Int?>): Double {
        val start = System.nanoTime().toDouble()
        map[-1] = null
        return System.nanoTime() - start
    }

    private fun searchByKey(map: Map<Int, Int?>): Double {
        val start = System.nanoTime().toDouble()
        val b: Any? = map[map.size / 2]
        return System.nanoTime() - start
    }

    private fun removing(map: MutableMap<Int, Int?>): Double {
        val start = System.nanoTime().toDouble()
        map.remove(map.size / 2)
        return System.nanoTime() - start
    }

    private fun createMap(map: MutableMap<Int, Int?>, size: Int): MutableMap<Int, Int?> {
        for (i in 0 until size) {
            map[i] = i
        }
        return map
    }


}