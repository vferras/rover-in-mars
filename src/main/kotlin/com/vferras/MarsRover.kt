package com.vferras

import java.util.*

object Memory { val reader = Scanner(System.`in`) }
data class UserInput(val inputName: String, val message: String, val type: Any, val inputConstraint: String? = null)

val pointsRegex = { "([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])" }
val obstaclesRegex = { "^\$|(.*[0-9].*:.*[0-9].*)" }

fun main() {
    val points = checkInput(
        mutableMapOf(),
        mutableListOf(
            UserInput("mapSizeX", "Insert horizontal map size:", String::class, pointsRegex()),
            UserInput("mapSizeY", "Insert vertical map size:", String::class, pointsRegex()),
            UserInput("x", "Insert horizontal initial rover position:", String::class, pointsRegex()),
            UserInput("y", "Insert vertical initial rover position:", String::class, pointsRegex()),
            UserInput("z", "Insert initial rover direction:", String::class, "[nsew]"),
            UserInput("obstacles", "Insert obstacles in format x:y x:y N", String::class, obstaclesRegex())
        )
    )

    readInputAndMove(points)
}

tailrec fun checkInput(points: MutableMap<String, Any>, input: MutableList<UserInput>): MutableMap<String, Any> {
    val firstInput = input.first()

    println(firstInput.message)

    when (firstInput.inputConstraint == null) {
        true -> {
            points[firstInput.inputName] = Memory.reader.nextLine()
            input.removeFirst()
        }
        false -> {
            val nextLine = Memory.reader.nextLine()
            if(firstInput.inputConstraint.toRegex().containsMatchIn(nextLine)) {
                points[firstInput.inputName] = nextLine
                input.removeFirst()
            }
            else println("Incorrect value. Try again")
        }
    }

    return if(input.isEmpty()) points else checkInput(points, input)
}

fun readInputAndMove(points: MutableMap<String, Any>) {
    tailrec fun recursiveReadInputAndMove(points: MutableMap<String, Any>) {
        points
            .changeDataTypeForNumericInputs()
            .printMovementInfo()
            .readUserInput()
            .checkEdge()
            .ifAnyObstacle().thenRollbackMovement(points)

        recursiveReadInputAndMove(points)
    }

    return recursiveReadInputAndMove(points)
}

fun MutableMap<String, Any>.changeDataTypeForNumericInputs(): MutableMap<String, Any> {
    this["mapSizeX"] = this["mapSizeX"].toString().toInt()
    this["mapSizeY"] = this["mapSizeY"].toString().toInt()
    this["x"] = this["x"].toString().toInt()
    this["y"] = this["y"].toString().toInt()

    return this
}

private fun MutableMap<String, Any>.printMovementInfo(): MutableMap<String, Any> {
    println("Rover is at x:${this["x"] as Int} y:${this["y"] as Int} facing:${this["z"] as String}")
    println("Insert command (f = forward, b = backward, l = turn left, r = turn right):")

    return this
}

fun MutableMap<String, Any>.readUserInput(): MutableMap<String, Any> {
    when(Memory.reader.next()) {
        "f" -> moveForward(this).markLastMovement("f")
        "b" -> moveBackward(this).markLastMovement("b")
        "l" -> turnLeft(this)
        "r" -> turnRight(this)
        else -> println("Incorrect direction. Try again.")
    }

    return this
}
