package launchenthusiast.domain.models

/**
 * Enumuerator for representing the different units of measurement used in this application
 */
enum class UnitOfMeasurement(var unit: String) {
    kN("Kilonewton"),
    m("Meter"),
    kg("Kilogram")
}