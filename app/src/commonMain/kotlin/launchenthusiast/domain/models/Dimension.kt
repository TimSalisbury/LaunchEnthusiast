package launchenthusiast.domain.models

/**
 * Class for representing the dimensions of an object in the database
 */
data class Dimension(
    var diameter: Double,
    var height: Double,
    var mass: Double,
    var lengthUnit: UnitOfMeasurement,
    var massUnit: UnitOfMeasurement
)