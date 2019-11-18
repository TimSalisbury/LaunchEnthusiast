package launchenthusiast.domain.models

data class LaunchLibraryLaunch (
    val id : Int,
    val name : String,
    val windowstart : String,
    val windowend : String,
    val net : String,
    val vidURLs : List<String>,
    val location : Map<String, String>,
    val rocket : Map<String, String>
)