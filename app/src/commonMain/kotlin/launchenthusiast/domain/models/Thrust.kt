package launchenthusiast.domain.models

/**
 * Class for representing thrust values
 */
data class Thrust(
    var thrustVacuum: Double,
    var thrustSealevel: Double,
    var thrustUnit: UnitOfMeasurement
)