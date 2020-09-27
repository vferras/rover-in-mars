import java.util.*
import java.util.regex.Pattern

object Memory { val reader = Scanner(System.`in`) }

fun main() {
    readInputAndMove {
        mutableMapOf(
                "mapSizeX" to printAndReadInput<Int>("Insert horizontal map size:"),
                "mapSizeY" to printAndReadInput<Int>("Insert vertical map size:"),
                "x" to printAndReadInput<Int>("Insert horizontal initial rover position:"),
                "y" to printAndReadInput<Int>("Insert vertical initial rover position:"),
                "z" to printAndReadInput<String>("Insert initial rover direction:")
        )
    }
}

fun readInputAndMove(points: () -> MutableMap<String, Any>) {
    tailrec fun recursiveReadInputAndMove(points: MutableMap<String, Any>) {
        printMovementInfo(points["x"] as Int, points["y"] as Int, points["z"] as String)

        when(Memory.reader.next()) {
            "f" -> moveForward(points)
            "b" -> moveBackward(points)
            "l" -> turnLeft(points)
            "r" -> turnRight(points)
            else -> println("Incorrect direction. Try again.")
        }
        recursiveReadInputAndMove(checkEdge(points))
    }

    return recursiveReadInputAndMove(points())
}

fun moveForward(points: MutableMap<String, Any>): Map<String, Any> {
    when(points["z"]) {
        "n" -> points["y"] = points["y"] as Int + 1
        "s" -> points["y"] = points["y"] as Int - 1
        "e" -> points["x"] = points["x"] as Int + 1
        "w" -> points["x"] = points["x"] as Int - 1
    }
    return points
}

fun moveBackward(points: MutableMap<String, Any>): Map<String, Any> {
    when(points["z"]) {
        "n" -> points["y"] = points["y"] as Int - 1
        "s" -> points["y"] = points["y"] as Int + 1
        "e" -> points["x"] = points["x"] as Int - 1
        "w" -> points["x"] = points["x"] as Int + 1
    }
    return points
}

fun turnLeft(points: MutableMap<String, Any>): Map<String, Any> {
    when(points["z"]) {
        "n" -> points["z"] = "w"
        "s" -> points["z"] = "e"
        "e" -> points["z"] = "n"
        "w" -> points["z"] = "s"
    }
    return points
}

fun turnRight(points: MutableMap<String, Any>): Map<String, Any> {
    when(points["z"]) {
        "n" -> points["z"] = "e"
        "s" -> points["z"] = "w"
        "e" -> points["z"] = "s"
        "w" -> points["z"] = "n"
    }
    return points
}

fun checkEdge(points: MutableMap<String, Any>): MutableMap<String, Any> {
    fun replacePointIntoMap(map: MutableMap<String, Any>, point: Pair<String, Any>): MutableMap<String, Any> {
        when(point.first) {
            "x" -> map["x"] = point.second
            "y" -> map["y"] = point.second
        }
        return map
    }

    if(points["y"] as Int > points["mapSizeY"] as Int) replacePointIntoMap(points, Pair("y", 1))
    if(points["y"] as Int <= 0) replacePointIntoMap(points, Pair("y", points["mapSizeY"] as Int))
    if(points["x"] as Int > points["mapSizeX"] as Int) replacePointIntoMap(points, Pair("x", 1))
    if(points["x"] as Int <= 0) replacePointIntoMap(points, Pair("x", points["mapSizeX"] as Int))

    return points
}

inline fun <reified T: Any> printAndReadInput(message: String): T {
    println(message)

    return when(T::class) {
        Int::class -> {
            try { Memory.reader.nextInt() as T }
            catch (e: InputMismatchException) {
                Memory.reader.next()
                println("Incorrect value. Defaulted to 1")
                1 as T
            }
        }
        String::class -> {
            if(Memory.reader.hasNext(Pattern.compile("[nsew]"))) Memory.reader.next() as T
            else {
                Memory.reader.next()
                println("Incorrect value. Defaulted to n")
                "n" as T
            }
        }
        else -> Memory.reader.next() as T
    }
}

private val printMovementInfo = { x: Int, y: Int, z: String ->
    println("Rover is at x:$x y:$y facing:$z")
    println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")
}
