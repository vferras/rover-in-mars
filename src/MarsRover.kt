import java.util.*

fun main() {
    val reader = Scanner(System.`in`)

    val initialHorizontalMapSize = printAndRead<Int>("Insert horizontal map size:", reader)
    val initialVerticalMapSize = printAndRead<Int>("Insert vertical map size:", reader)

    var roverPositionX = printAndRead<Int>("Insert horizontal initial rover position:", reader)
    var roverPositionY = printAndRead<Int>("Insert vertical initial rover position:", reader)
    var roverPositionZ = printAndRead<String>("Insert initial rover direction:", reader)

    do {
        println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")

        when(reader.next()) {
            "f" -> {
                val (newRoverPositionX, newRoverPositionY) = whenForward(roverPositionZ, roverPositionX, roverPositionY)
                roverPositionX = newRoverPositionX
                roverPositionY = newRoverPositionY
            }
            "b" -> {
                val (newRoverPositionX, newRoverPositionY) = whenBackward(roverPositionZ, roverPositionX, roverPositionY)
                roverPositionX = newRoverPositionX
                roverPositionY = newRoverPositionY
            }
            "l" -> roverPositionZ = whenLeft(roverPositionZ)
            "r" -> roverPositionZ = whenRight(roverPositionZ)
        }

        println(String.format("Rover is at x:%d y:%d facing:%s", roverPositionX, roverPositionY, roverPositionZ))
    } while (true)
}

private fun whenForward(currentPositionZ: String, currentPositionX: Int, currentPositionY: Int): Pair<Int, Int> {
    return when(currentPositionZ) {
        "n" -> Pair(currentPositionX, currentPositionY + 1)
        "s" -> Pair(currentPositionX, currentPositionY - 1)
        "e" -> Pair(currentPositionX + 1, currentPositionY)
        "w" -> Pair(currentPositionX - 1, currentPositionY)
        else -> Pair(0, 0)
    }
}

private fun whenBackward(currentPositionZ: String, currentPositionX: Int, currentPositionY: Int): Pair<Int, Int> {
    return when(currentPositionZ) {
        "n" -> Pair(currentPositionX, currentPositionY - 1)
        "s" -> Pair(currentPositionX, currentPositionY + 1)
        "e" -> Pair(currentPositionX - 1, currentPositionY)
        "w" -> Pair(currentPositionX + 1, currentPositionY)
        else -> Pair(0, 0)
    }
}

private fun whenLeft(currentPositionZ: String): String {
    return when(currentPositionZ) {
        "n" -> "w"
        "s" -> "e"
        "e" -> "n"
        "w" -> "s"
        else -> ""
    }
}

private fun whenRight(currentPositionZ: String): String {
    return when(currentPositionZ) {
        "n" -> "e"
        "s" -> "w"
        "e" -> "s"
        "w" -> "n"
        else -> ""
    }
}

private inline fun <reified T: Any> printAndRead(message: String, reader: Scanner): T {
    println(message)

    return when(T::class) {
        Int::class -> reader.nextInt() as T
        String::class -> reader.next() as T
        else -> reader.next() as T
    }
}
