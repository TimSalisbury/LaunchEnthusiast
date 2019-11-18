package launchenthusiast.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import java.io.File
import java.io.FileOutputStream

class ImageCache{
    companion object : Cache<Bitmap>(){
        override val extension: String = "png"
        override val fileParser =  fun(f : File): Bitmap{
            return BitmapFactory.decodeFile(f.absolutePath)
        }
        override val fileSaver = fun(b : Bitmap, key : Int){
            val file = File(baseDirectory, "$key.$extension")
            val out = FileOutputStream(file)
            b.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
        }
    }
}