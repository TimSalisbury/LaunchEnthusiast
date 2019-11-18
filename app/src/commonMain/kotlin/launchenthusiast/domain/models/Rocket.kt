package launchenthusiast.domain.models

data class Rocket(
    var id: Int,
    var designer : String,
    var manufacturer : String,
    var name : String,
    var reuseable : Boolean,
    var costNew : Int,
    var costUsed : Int,
    var currency : String,
    var launches: Launches,
    var firstFlight : String,
    var launchSites : List<String>,
    var stages : Map<String, Stage>,
    var payload: Payload,
    var wikipedia : String,
    var image : String
)