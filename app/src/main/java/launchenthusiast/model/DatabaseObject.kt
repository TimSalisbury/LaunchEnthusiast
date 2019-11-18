package launchenthusiast.domain.models

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.jvm.JvmMultifileClass
import kotlin.reflect.KClass

enum class DatabaseObject(var clazz : Class<*>, var listClazz : Type, var endpoint : String) {
    ENGINE(Engine::class.java, object: TypeToken<List<Engine>>(){}.type, "engines"),
    ROCKET(Rocket::class.java, object: TypeToken<List<Rocket>>(){}.type, "rockets"),
    LAUNCH_LIBRARY_LAUNCH(LaunchLibraryLaunch::class.java, object: TypeToken<List<LaunchLibraryLaunch>>(){}.type, "launch")
}