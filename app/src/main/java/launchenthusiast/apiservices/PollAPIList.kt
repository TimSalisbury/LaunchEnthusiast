package launchenthusiast.apiservices

import android.net.Uri
import android.os.AsyncTask
import com.google.gson.Gson
import launchenthusiast.cache.APICache
import launchenthusiast.domain.models.DatabaseObject
import java.net.URL

class PollAPIList<T> constructor(private val postExecutable: (List<T>)->Unit,
                     private val parameters : String?, private val api : API):
    AsyncTask<DatabaseObject, Unit, List<T>>(){

     constructor(postExecutable: (List<T>) -> Unit, api : API) : this(postExecutable, null, api)

    override fun doInBackground(vararg params: DatabaseObject): List<T>? {
//        val URIBuilder = Uri.Builder()
//        URIBuilder
//            .scheme("https")
//            .authority(api.endpoint)
//            .appendPath(params[0].endpoint)

        val request = api.endpoint + params[0].endpoint + (parameters?: "")

        val json = if(APICache.containsKey(request)){
            APICache[request]
        }else{
            val tmp = URL(request).readText()
            APICache[request] = tmp
            tmp
        }
        return Gson().fromJson(json, params[0].listClazz)
    }

    override fun onPostExecute(result: List<T>?) {
        result?.let{postExecutable.invoke(result)}
    }
}