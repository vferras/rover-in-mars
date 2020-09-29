package com.vferras

fun moveForward(points: MutableMap<String, Any>): MutableMap<String, Any> {
    when(points["z"]) {
        "n" -> points["y"] = points["y"] as Int + 1
        "s" -> points["y"] = points["y"] as Int - 1
        "e" -> points["x"] = points["x"] as Int + 1
        "w" -> points["x"] = points["x"] as Int - 1
    }
    return points
}

fun moveBackward(points: MutableMap<String, Any>): MutableMap<String, Any> {
    when(points["z"]) {
        "n" -> points["y"] = points["y"] as Int - 1
        "s" -> points["y"] = points["y"] as Int + 1
        "e" -> points["x"] = points["x"] as Int - 1
        "w" -> points["x"] = points["x"] as Int + 1
    }
    return points
}

fun turnLeft(points: MutableMap<String, Any>): MutableMap<String, Any> {
    when(points["z"]) {
        "n" -> points["z"] = "w"
        "s" -> points["z"] = "e"
        "e" -> points["z"] = "n"
        "w" -> points["z"] = "s"
    }
    return points
}

fun turnRight(points: MutableMap<String, Any>): MutableMap<String, Any> {
    when(points["z"]) {
        "n" -> points["z"] = "e"
        "s" -> points["z"] = "w"
        "e" -> points["z"] = "s"
        "w" -> points["z"] = "n"
    }
    return points
}

fun MutableMap<String, Any>.markLastMovement(movement: String): Map<String, Any> {
    this["lastMovement"] = movement
    return this
}

fun MutableMap<String, Any>.checkEdge(): MutableMap<String, Any> {
    fun replacePointIntoMap(map: MutableMap<String, Any>, point: Pair<String, Any>): MutableMap<String, Any> {
        when(point.first) {
            "x" -> map["x"] = point.second
            "y" -> map["y"] = point.second
        }
        return map
    }

    if(this["y"] as Int > this["mapSizeY"] as Int) replacePointIntoMap(this, Pair("y", 1))
    if(this["y"] as Int <= 0) replacePointIntoMap(this, Pair("y", this["mapSizeY"] as Int))
    if(this["x"] as Int > this["mapSizeX"] as Int) replacePointIntoMap(this, Pair("x", 1))
    if(this["x"] as Int <= 0) replacePointIntoMap(this, Pair("x", this["mapSizeX"] as Int))

    return this
}

fun MutableMap<String, Any>.ifAnyObstacle(): Boolean {
    if(!this.containsKey("obstacles") || this["obstacles"] == "") return false

    (this["obstacles"] as String).split(" ").forEach {
        val x = it.split(":")[0].toInt()
        val y = it.split(":")[1].toInt()

        if(this["x"] as Int == x && this["y"] as Int == y) {
            println("Obstacle encountered at x: $x, y: $y. Movement cancelled")
            return true
        }
    }

    return false
}

fun Boolean.thenRollbackMovement(points: MutableMap<String, Any>): Map<String, Any> {
    if(!this) return points

    return when(points["lastMovement"]) {
        "f" -> moveBackward(points)
        "b" -> moveForward(points)
        else -> points
    }
}
