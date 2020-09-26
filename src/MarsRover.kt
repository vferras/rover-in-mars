import java.util.*

fun main() {
    val reader = Scanner(System.`in`)

    val initialHorizontalMapSize = printAndRead<Int>("Insert horizontal map size:", reader)
    val initialVerticalMapSize = printAndRead<Int>("Insert vertical map size:", reader)

    var initialRoverPositionX = printAndRead<Int>("Insert horizontal initial rover position:", reader)
    var initialRoverPositionY = printAndRead<Int>("Insert vertical initial rover position:", reader)
    var initialRoverPositionZ = printAndRead<String>("Insert initial rover direction:", reader)

    readInputAndMove(reader, initialRoverPositionZ, initialRoverPositionX, initialRoverPositionY)
}

fun readInputAndMove(reader: Scanner, positionZ: String, positionX: Int, positionY: Int) {
    println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")

    val result = when(reader.next()) {
        "f" -> {
            val (newPositionX, newPositionY) = whenForward(positionZ, positionX, positionY)
            Triple(positionZ, newPositionX, newPositionY)
        }
        "b" -> {
            val (newPositionX, newPositionY) = whenBackward(positionZ, positionX, positionY)
            Triple(positionZ, newPositionX, newPositionY)
        }
        "l" -> Triple(whenLeft(positionZ), positionX, positionY)
        "r" -> Triple(whenRight(positionZ), positionX, positionY)
        else -> {
            println("Incorrect direction. Try again.")
            Triple(positionZ, positionX, positionY)
        }
    }

    println(String.format("Rover is at x:%d y:%d facing:%s", result.second, result.third, result.first))
    readInputAndMove(reader = reader, positionZ = result.first, positionX = result.second, positionY = result.third)
}

fun whenForward(currentPositionZ: String, currentPositionX: Int, currentPositionY: Int): Pair<Int, Int> {
    return when(currentPositionZ) {
        "n" -> Pair(currentPositionX, currentPositionY + 1)
        "s" -> Pair(currentPositionX, currentPositionY - 1)
        "e" -> Pair(currentPositionX + 1, currentPositionY)
        "w" -> Pair(currentPositionX - 1, currentPositionY)
        else -> Pair(currentPositionX, currentPositionY)
    }
}

fun whenBackward(currentPositionZ: String, currentPositionX: Int, currentPositionY: Int): Pair<Int, Int> {
    return when(currentPositionZ) {
        "n" -> Pair(currentPositionX, currentPositionY - 1)
        "s" -> Pair(currentPositionX, currentPositionY + 1)
        "e" -> Pair(currentPositionX - 1, currentPositionY)
        "w" -> Pair(currentPositionX + 1, currentPositionY)
        else -> Pair(currentPositionX, currentPositionY)
    }
}

fun whenLeft(currentPositionZ: String): String {
    return when(currentPositionZ) {
        "n" -> "w"
        "s" -> "e"
        "e" -> "n"
        "w" -> "s"
        else -> currentPositionZ
    }
}

fun whenRight(currentPositionZ: String): String {
    return when(currentPositionZ) {
        "n" -> "e"
        "s" -> "w"
        "e" -> "s"
        "w" -> "n"
        else -> currentPositionZ
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
