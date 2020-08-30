package xyz.luchengeng.authentication.service

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.minio.starter.bean.MinIOClient
import xyz.luchengeng.minio.starter.bean.ObjectMap
import java.io.InputStream
import java.util.*

@Service
class ContentService @Autowired constructor(private val map: ObjectMap,private val rawClient : MinioClient,@Value("\${min.io.bucket}") val bucketName : String) {
    fun getPic(id : UUID): ByteArray{
        return (map[id.toString()]?:throw NotFoundException("Pic Not Found")) as ByteArray
    }

    fun saveContent(content : Any): UUID{
        val id = UUID.randomUUID()
        map[id.toString()] = content
        return id
    }
    fun delContent(id : UUID){
        map.remove(id.toString())
    }

    fun saveContent(file : MultipartFile):UUID{
        val id = UUID.randomUUID()
        rawClient.putObject(bucketName,id.toString(),file.inputStream,file.size,file.contentType)
        return id;
    }

    fun getFile(id : UUID) : Pair<InputStream,Long>{
        val stat = rawClient.statObject(bucketName,id.toString())
        return Pair(rawClient.getObject(bucketName,id.toString()),stat.length())
    }
}