import java.util.*

fun main() {
    val reader = Scanner(System.`in`)

    val points = mapOf(
            "mapSizeX" to printAndRead<Int>("Insert horizontal map size:", reader),
            "mapSizeY" to printAndRead<Int>("Insert vertical map size:", reader),
            "x" to printAndRead<Int>("Insert horizontal initial rover position:", reader),
            "y" to printAndRead<Int>("Insert vertical initial rover position:", reader),
            "z" to printAndRead<String>("Insert initial rover direction:", reader)
    )

    readInputAndMove(reader, points)
}

fun readInputAndMove(reader: Scanner, points: Map<String, Any>) {
    tailrec fun recursive(reader: Scanner, points: Map<String, Any>) {
        println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")

        val result = when(reader.next()) {
            "f" -> {
                val (newPositionX, newPositionY) = whenForward(points)
                mapOf("z" to points["z"].toString(), "x" to newPositionX, "y" to newPositionY)
            }
            "b" -> {
                val (newPositionX, newPositionY) = whenBackward(points)
                mapOf("z" to points["z"].toString(), "x" to newPositionX, "y" to newPositionY)
            }
            "l" -> mapOf("z" to whenLeft(points), "x" to points["x"] as Int, "y" to points["y"] as Int)
            "r" -> mapOf("z" to whenRight(points), "x" to points["x"] as Int, "y" to points["y"] as Int)
            else -> {
                println("Incorrect direction. Try again.")
                points
            }
        }

        println(String.format("Rover is at x:%d y:%d facing:%s", result["x"], result["y"], result["z"]))
        recursive(reader = reader, result)
    }

    return recursive(reader, points)
}

fun whenForward(points: Map<String, Any>): Pair<Int, Int> {
    return when(points["z"]) {
        "n" -> Pair(points["x"] as Int, points["y"] as Int + 1)
        "s" -> Pair(points["x"] as Int, points["y"] as Int - 1)
        "e" -> Pair(points["x"] as Int + 1, points["y"] as Int)
        "w" -> Pair(points["x"] as Int - 1, points["y"] as Int)
        else -> Pair(points["x"] as Int, points["y"] as Int)
    }
}

fun whenBackward(points: Map<String, Any>): Pair<Int, Int> {
    return when(points["z"]) {
        "n" -> Pair(points["x"] as Int, points["y"] as Int - 1)
        "s" -> Pair(points["x"] as Int, points["y"] as Int + 1)
        "e" -> Pair(points["x"] as Int - 1, points["y"] as Int)
        "w" -> Pair(points["x"] as Int + 1, points["y"] as Int)
        else -> Pair(points["x"] as Int, points["y"] as Int)
    }
}

fun whenLeft(points: Map<String, Any>): String {
    return when(points["z"]) {
        "n" -> "w"
        "s" -> "e"
        "e" -> "n"
        "w" -> "s"
        else -> points["z"].toString()
    }
}

fun whenRight(points: Map<String, Any>): String {
    return when(points["z"]) {
        "n" -> "e"
        "s" -> "w"
        "e" -> "s"
        "w" -> "n"
        else -> points["z"].toString()
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
