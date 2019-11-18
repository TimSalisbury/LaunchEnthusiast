package launchenthusiast.cache

import android.content.Context
import java.io.File

class APICache{
    companion object : Cache<String>() {
        override var fileParser = { f: File -> f.readText() }
        override val extension = "json"
        override val fileSaver = fun(s : String, key : Int){
            File(baseDirectory, "$key.$extension").writeText(s)
        }
    }
}