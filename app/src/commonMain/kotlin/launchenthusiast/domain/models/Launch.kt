package launchenthusiast.domain.models

data class Launch(
    var id : Int,
    var name : String,
    var windowStart : String,
    var windowEnd : String,
    var net : String,
    var lsp : String
)