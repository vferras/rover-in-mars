import java.util.*

object MarsRover {
    @JvmStatic
    fun main(args: Array<String>) {
        val reader = Scanner(System.`in`)
        println("Insert horizontal map size:")
        val sizex = reader.nextInt()
        println("Insert vertical map size:")
        val sizey = reader.nextInt()
        println("Insert horizontal initial rover position:")
        var roverx = reader.nextInt()
        println("Insert vertical initial rover position:")
        var rovery = reader.nextInt()
        println("Insert initial rover direction:")
        var roverz = reader.next() //n = north, e = east, w = west, s = south
        do {
            println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")
            val command = reader.next()
            if (command == "f") {
                if (roverz == "n") {
                    rovery += 1
                }
                if (roverz == "w") {
                    roverx -= 1
                }
                if (roverz == "s") {
                    rovery -= 1
                }
                if (roverz == "e") {
                    roverx += 1
                }
            }
            if (command == "b") {
                if (roverz == "n") {
                    rovery -= 1
                }
                if (roverz == "w") {
                    roverx += 1
                }
                if (roverz == "s") {
                    rovery += 1
                }
                if (roverz == "e") {
                    roverx -= 1
                }
            }
            if (command == "l") {
                if (roverz == "n") {
                    roverz = "w"
                }
                if (roverz == "w") {
                    roverz = "s"
                }
                if (roverz == "s") {
                    roverz = "e"
                }
                if (roverz == "e") {
                    roverz = "n"
                }
            }
            if (command == "r") {
                if (roverz == "n") {
                    roverz = "e"
                }
                if (roverz == "e") {
                    roverz = "s"
                }
                if (roverz == "s") {
                    roverz = "w"
                }
                if (roverz == "w") {
                    roverz = "n"
                }
            }
            println(String.format("Rover is at x:%d y:%d facing:%s", roverx, rovery, roverz))
        } while (true)
    }
}