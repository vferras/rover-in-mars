package com.vferras

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MarsRoverTest {
    @Test
    fun `given a position when moving forward then a new position is set`() {
        val positionAndResult1 = Pair(
                mutableMapOf<String, Any>("z" to "n", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "n", "x" to 5, "y" to 6)
        )
        val positionAndResult2 = Pair(
                mutableMapOf<String, Any>("z" to "s", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "s", "x" to 5, "y" to 4)
        )
        val positionAndResult3 = Pair(
                mutableMapOf<String, Any>("z" to "e", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "e", "x" to 6, "y" to 5)
        )
        val positionAndResult4 = Pair(
                mutableMapOf<String, Any>("z" to "w", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "w", "x" to 4, "y" to 5)
        )

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { moveForward(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving backward then a new position is set`() {
        val positionAndResult1 = Pair(
                mutableMapOf<String, Any>("z" to "n", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "n", "x" to 5, "y" to 4)
        )
        val positionAndResult2 = Pair(
                mutableMapOf<String, Any>("z" to "s", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "s", "x" to 5, "y" to 6)
        )
        val positionAndResult3 = Pair(
                mutableMapOf<String, Any>("z" to "e", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "e", "x" to 4, "y" to 5)
        )
        val positionAndResult4 = Pair(
                mutableMapOf<String, Any>("z" to "w", "x" to 5, "y" to 5),
                mutableMapOf<String, Any>("z" to "w", "x" to 6, "y" to 5)
        )

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { moveBackward(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving left then a new position is set`() {
        val positionAndResult1 = Pair(mutableMapOf<String, Any>("z" to "n"), mutableMapOf("z" to "w"))
        val positionAndResult2 = Pair(mutableMapOf<String, Any>("z" to "s"), mutableMapOf("z" to "e"))
        val positionAndResult3 = Pair(mutableMapOf<String, Any>("z" to "e"), mutableMapOf("z" to "n"))
        val positionAndResult4 = Pair(mutableMapOf<String, Any>("z" to "w"), mutableMapOf("z" to "s"))

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { turnLeft(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving right then a new position is set`() {
        val positionAndResult1 = Pair(mutableMapOf<String, Any>("z" to "n"), mutableMapOf("z" to "e"))
        val positionAndResult2 = Pair(mutableMapOf<String, Any>("z" to "s"), mutableMapOf("z" to "w"))
        val positionAndResult3 = Pair(mutableMapOf<String, Any>("z" to "e"), mutableMapOf("z" to "s"))
        val positionAndResult4 = Pair(mutableMapOf<String, Any>("z" to "w"), mutableMapOf("z" to "n"))

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { turnRight(it.first) == it.second }
        }
    }

    @Test
    fun `given an edge position when moving forward then a new position in the beginning is set`() {
        val positionAndResult1 = Pair(
                mutableMapOf<String, Any>("x" to 1, "y" to 11, "mapSizeX" to 10, "mapSizeY" to 10),
                mutableMapOf<String, Any>("x" to 1, "y" to 1, "mapSizeX" to 10, "mapSizeY" to 10)
        )
        val positionAndResult2 = Pair(
                mutableMapOf<String, Any>("x" to 1, "y" to 0, "mapSizeX" to 10, "mapSizeY" to 10),
                mutableMapOf<String, Any>("x" to 1, "y" to 10, "mapSizeX" to 10, "mapSizeY" to 10)
        )
        val positionAndResult3 = Pair(
                mutableMapOf<String, Any>("x" to 11, "y" to 1, "mapSizeX" to 10, "mapSizeY" to 10),
                mutableMapOf<String, Any>("x" to 1, "y" to 1, "mapSizeX" to 10, "mapSizeY" to 10)
        )
        val positionAndResult4 = Pair(
                mutableMapOf<String, Any>("x" to 0, "y" to 1, "mapSizeX" to 10, "mapSizeY" to 10),
                mutableMapOf<String, Any>("x" to 10, "y" to 1, "mapSizeX" to 10, "mapSizeY" to 10)
        )

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { checkEdge(it.first) == it.second }
        }
    }
}
