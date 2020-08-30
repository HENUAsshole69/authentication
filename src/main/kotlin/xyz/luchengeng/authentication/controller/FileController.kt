package xyz.luchengeng.authentication.controller

import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import xyz.luchengeng.authentication.repo.FileRepo
import xyz.luchengeng.authentication.service.ContentService
import xyz.luchengeng.authentication.service.InventoryService
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.servlet.ServletContext


@Controller
class FileController @Autowired constructor(private val contentService: ContentService,private val servletContext: ServletContext,private val inventoryService: InventoryService,private val fileRepo: FileRepo){
    @PostMapping("/antique/{id}/inventory/file/{fileName}")
    fun doUpload(@RequestParam("file") multipartFile: MultipartFile,@RequestHeader("x-api-key") jwt : String,@PathVariable id : Long,@PathVariable fileName : String): ResponseEntity<Void> {
        inventoryService.addFileForAntiqueInventory(id,multipartFile,fileName)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/file/{uuid}")
    fun downloadFile1(@PathVariable uuid: UUID): ResponseEntity<InputStreamResource> {
        val file = fileRepo.findByIdOrNull(uuid)?:throw NotFoundException("File Not Found")
        val mediaType: MediaType =  MediaType.parseMediaType(servletContext.getMimeType(file.fileName))
        val fileContent = contentService.getFile(uuid)
        val resource = InputStreamResource(fileContent.first)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\$fileName") // Content-Type
                .contentType(mediaType)
                .contentLength(fileContent.second)
                .body(resource)
    }
}