import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MarsRoverTest {
    @Test
    fun `given a position when moving forward then a new position is set`() {
        val positionAndResult1 = Pair(mapOf("z" to "n", "x" to 5, "y" to 5), Pair(5, 6))
        val positionAndResult2 = Pair(mapOf("z" to "s", "x" to 5, "y" to 5), Pair(5, 4))
        val positionAndResult3 = Pair(mapOf("z" to "e", "x" to 5, "y" to 5), Pair(6, 5))
        val positionAndResult4 = Pair(mapOf("z" to "w", "x" to 5, "y" to 5), Pair(4, 5))

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenForward(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving backward then a new position is set`() {
        val positionAndResult1 = Pair(mapOf("z" to "n", "x" to 1, "y" to 1), Pair(1, 0))
        val positionAndResult2 = Pair(mapOf("z" to "s", "x" to 1, "y" to 1), Pair(1, 2))
        val positionAndResult3 = Pair(mapOf("z" to "e", "x" to 1, "y" to 1), Pair(0, 1))
        val positionAndResult4 = Pair(mapOf("z" to "w", "x" to 1, "y" to 1), Pair(2, 1))

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenBackward(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving left then a new position is set`() {
        val positionAndResult1 = Pair(mapOf("z" to "n"), "w")
        val positionAndResult2 = Pair(mapOf("z" to "s"), "e")
        val positionAndResult3 = Pair(mapOf("z" to "e"), "n")
        val positionAndResult4 = Pair(mapOf("z" to "w"), "s")

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenLeft(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving right then a new position is set`() {
        val positionAndResult1 = Pair(mapOf("z" to "n"), "e")
        val positionAndResult2 = Pair(mapOf("z" to "s"), "w")
        val positionAndResult3 = Pair(mapOf("z" to "e"), "s")
        val positionAndResult4 = Pair(mapOf("z" to "w"), "n")

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenRight(it.first) == it.second }
        }
    }
}
