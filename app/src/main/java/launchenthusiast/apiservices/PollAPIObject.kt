package launchenthusiast.apiservices

import android.os.AsyncTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import launchenthusiast.domain.models.DatabaseObject
import java.lang.reflect.Executable
import java.net.URL

class PollAPIObject<T>(private val postExecutable: (T)->Unit,
                       private val objectID : Int) : AsyncTask<DatabaseObject, Unit, T>() {

    override fun doInBackground(vararg params: DatabaseObject): T {
        val json = URL(PollAPIBase.API_URL + params[0].endpoint + "?id=" + objectID).readText()
        return Gson().fromJson(json, object: TypeToken<T>(){}.type)
    }

    override fun onPostExecute(result: T?) {
        result?.let{postExecutable.invoke(result)}
    }
}