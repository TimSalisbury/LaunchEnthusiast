package launchenthusiast.domain.models

/**
 * Data class for representing the Engine object in the database
 */
data class Engine(
    var id: Int,
    var name: String,
    var designer: String,
    var manufacturer: String,
    var propellents: List<String>,
    var thrust: Thrust,
    var dimension: Dimension,
    var cycle: String,
    var wikipedia: String,
    var image: String
)