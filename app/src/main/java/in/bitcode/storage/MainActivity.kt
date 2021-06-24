package `in`.bitcode.storage

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //internal storage 1
        /*
        var fout : FileOutputStream = openFileOutput("my_file1.txt", Activity.MODE_PRIVATE or Activity.MODE_APPEND)
        fout.write("BitCode is the best place to learn!\n".toByteArray()) //"string".getBytes()
        fout.write("Android is excellent\n".toByteArray())
        fout.close()

        var fin : FileInputStream = openFileInput("my_file1.txt")
        var count = 0
        var data = ByteArray(1024*2)
        count = fin.read(data)
        while(count != -1) {
            Log.e("tag", String(data, 0, count) )
            count = fin.read(data)
        }
        fin.close()

        //deleteFile("my_file1.txt")
        var myDir : File = getDir("my_dir", Activity.MODE_PRIVATE)
        //deleteFile("my_dir")
        */

        //internal storage 2
        var root = filesDir //root = getFilesDir()
        mt(filesDir.absolutePath)


        var newFile = File(root, "new_file.txt")
        if(!newFile.exists()) {
            newFile.createNewFile()
        }

        var fout = FileOutputStream(newFile)
        fout.write("this is a sample string....".toByteArray())
        fout.close()

        var fin = FileInputStream(newFile)
        var data = ByteArray(100)
        var count = fin.read(data)
        mt(String(data, 0, count))
        fin.close()

        var myDir = File(root, "my_dir")
        if(!myDir.exists()) {
            myDir.mkdir()
        }

        var anotherFile = File(myDir, "somefile.txt")
        if(!anotherFile.exists()) {
            anotherFile.createNewFile()
        }


        for( file in filesDir.listFiles()) {
            mt("path: ${file.absolutePath} isFile: ${file.isFile}  isDir: ${file.isDirectory}")
            if(file.isDirectory) {
                for(f in file.listFiles()) {
                    mt("    path: ${f.absolutePath} isFile: ${f.isFile}  isDir: ${f.isDirectory}")
                }
            }
        }

        mt("-------------------------------------------");
        listAllFilesFromDir( filesDir.parentFile, 0 )

        mt("Cache Dir: ${cacheDir.absolutePath}") //temporary dir getCacheDir()

    }

    private fun listAllFilesFromDir(file : File, tabs : Int) {

        var strTabs = ""
        for(i in (1..tabs)) {
            strTabs += "\t"
        }
        mt("$strTabs ${file.name}")
        if(file.isFile) {
            return
        }

        for(f in file.listFiles()) {
            listAllFilesFromDir(f, tabs+1)
        }

    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}