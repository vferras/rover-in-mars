import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MarsRoverTest {
    @Test
    fun `given a position when moving forward then a new position is set`() {
        val positionAndResult1 = Pair(Triple("n", 1, 1), Pair(1, 2))
        val positionAndResult2 = Pair(Triple("s", 1, 1), Pair(1, 0))
        val positionAndResult3 = Pair(Triple("e", 1, 1), Pair(2, 1))
        val positionAndResult4 = Pair(Triple("w", 1, 1), Pair(0, 1))

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenForward(it.first.first, it.first.second, it.first.third) == it.second }
        }
    }

    @Test
    fun `given a position when moving backward then a new position is set`() {
        val positionAndResult1 = Pair(Triple("n", 1, 1), Pair(1, 0))
        val positionAndResult2 = Pair(Triple("s", 1, 1), Pair(1, 2))
        val positionAndResult3 = Pair(Triple("e", 1, 1), Pair(0, 1))
        val positionAndResult4 = Pair(Triple("w", 1, 1), Pair(2, 1))

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenBackward(it.first.first, it.first.second, it.first.third) == it.second }
        }
    }

    @Test
    fun `given a position when moving left then a new position is set`() {
        val positionAndResult1 = Pair("n", "w")
        val positionAndResult2 = Pair("s", "e")
        val positionAndResult3 = Pair("e", "n")
        val positionAndResult4 = Pair("w", "s")

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenLeft(it.first) == it.second }
        }
    }

    @Test
    fun `given a position when moving right then a new position is set`() {
        val positionAndResult1 = Pair("n", "e")
        val positionAndResult2 = Pair("s", "w")
        val positionAndResult3 = Pair("e", "s")
        val positionAndResult4 = Pair("w", "n")

        listOf(positionAndResult1, positionAndResult2, positionAndResult3, positionAndResult4).forEach {
            assertTrue { whenRight(it.first) == it.second }
        }
    }
}
