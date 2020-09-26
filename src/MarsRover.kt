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
        val command = reader.next()
        if (command == "f") {
            if (roverPositionZ == "n") {
                roverPositionY += 1
            }
            if (roverPositionZ == "w") {
                roverPositionX -= 1
            }
            if (roverPositionZ == "s") {
                roverPositionY -= 1
            }
            if (roverPositionZ == "e") {
                roverPositionX += 1
            }
        }
        if (command == "b") {
            if (roverPositionZ == "n") {
                roverPositionY -= 1
            }
            if (roverPositionZ == "w") {
                roverPositionX += 1
            }
            if (roverPositionZ == "s") {
                roverPositionY += 1
            }
            if (roverPositionZ == "e") {
                roverPositionX -= 1
            }
        }
        if (command == "l") {
            if (roverPositionZ == "n") {
                roverPositionZ = "w"
            }
            if (roverPositionZ == "w") {
                roverPositionZ = "s"
            }
            if (roverPositionZ == "s") {
                roverPositionZ = "e"
            }
            if (roverPositionZ == "e") {
                roverPositionZ = "n"
            }
        }
        if (command == "r") {
            if (roverPositionZ == "n") {
                roverPositionZ = "e"
            }
            if (roverPositionZ == "e") {
                roverPositionZ = "s"
            }
            if (roverPositionZ == "s") {
                roverPositionZ = "w"
            }
            if (roverPositionZ == "w") {
                roverPositionZ = "n"
            }
        }
        println(String.format("Rover is at x:%d y:%d facing:%s", roverPositionX, roverPositionY, roverPositionZ))
    } while (true)
}

private inline fun <reified T: Any> printAndRead(message: String, reader: Scanner): T {
    println(message)

    return when(T::class) {
        Int::class -> reader.nextInt() as T
        String::class -> reader.next() as T
        else -> reader.next() as T
    }
}
