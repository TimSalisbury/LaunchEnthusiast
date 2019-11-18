package launchenthusiast.cache

import android.content.Context
import java.io.File

abstract class Cache<T> {

    private val cache : HashMap<Int, T> = HashMap()
    private val keys : MutableList<Int> = mutableListOf()
    private var initialized : Boolean = false
    protected var baseDirectory : File? = null
    abstract val fileParser : (File)->T
    abstract val fileSaver : (T, Int)->Unit
    abstract val extension : String

    fun initialize(appContext : Context){
        this.baseDirectory = appContext.externalCacheDir
        val appDir = baseDirectory
        baseDirectory!!
        appDir!!
        val jsonFiles = appDir.listFiles { file -> file.extension == extension}
        for(file in jsonFiles){
            keys.add(file.nameWithoutExtension.toInt())
        }
        initialized = true
    }

    operator  fun set(key : String, value : T){
        checkInitialized()
        fileSaver.invoke(value, key.toLowerCase().hashCode())
        cache[key.toLowerCase().hashCode()] = value
    }

    operator fun get(key : String) : T{
        checkInitialized()
        if(!cache.containsKey(key.toLowerCase().hashCode())){
            load(key.toLowerCase().hashCode())
        }
        return cache[key.toLowerCase().hashCode()]!!
    }

    private fun load(key : Int){
        cache[key] = fileParser.invoke(baseDirectory!!.resolve("$key.$extension"))
    }

    fun remove(key : String){
        checkInitialized()
        cache.remove(key.toLowerCase().hashCode())
    }

    fun clear(){
        checkInitialized()
        cache.clear()
    }

    fun containsKey(key : String) : Boolean{
        checkInitialized()
        return keys.contains(key.toLowerCase().hashCode())
    }

    private fun checkInitialized(){
        if(!initialized){
            throw Exception("You must initialize the cache first!")
        }
    }
}