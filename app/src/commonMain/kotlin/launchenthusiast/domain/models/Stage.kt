package launchenthusiast.domain.models

data class Stage (
    var numberOfEngine : Int,
    var thrust: Thrust,
    var enginesID : Int,
    var engineName : String,
    var dimension: Dimension,
    var isp : String
)