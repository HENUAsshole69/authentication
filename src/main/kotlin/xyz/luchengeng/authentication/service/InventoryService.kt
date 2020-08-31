package xyz.luchengeng.authentication.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import xyz.luchengeng.authentication.entity.File
import xyz.luchengeng.authentication.entity.Inventory
import xyz.luchengeng.authentication.entity.InventoryDto
import xyz.luchengeng.authentication.except.NotFoundException
import xyz.luchengeng.authentication.repo.AntiqueRepo
import xyz.luchengeng.authentication.repo.FileRepo
import xyz.luchengeng.authentication.repo.InventoryRepo
import java.util.*

@Service
class InventoryService @Autowired constructor(private val  inventoryRepo: InventoryRepo,private val antiqueRepo: AntiqueRepo,private val contentService: ContentService,private val fileRepo: FileRepo){
    fun setInventoryForId(id : Long, inventoryDto: InventoryDto) : Inventory{
        val antique = antiqueRepo.findByIdOrNull(id)?:throw NotFoundException("Antique with specified ID not found")
        inventoryRepo.findByAntiqueId(id).apply {
            if(this != null) inventoryRepo.delete(this)
        }
        return inventoryRepo.save(inventoryDto.toEntity(antique))
    }

    private fun findByAntiqueId(id : Long) = inventoryRepo.findByAntiqueId(id)?:throw NotFoundException("Inventory Not Found for Antique")

    fun findDtoByAntiqueId(id: Long) = findByAntiqueId(id).toDto()

    fun findFileForAntiqueInventory(id : Long) : List<File>{
        val inventory = this.findByAntiqueId(id)
        return inventory.files
    }

    fun modInventoryByAntiqueId(id: Long,func: (Inventory)->Unit){
        val inventory = findByAntiqueId(id)
        func(inventory)
        inventoryRepo.save(inventory)
    }

    fun delFileForAntiqueInventory(id : Long,fileId: Long){
        modInventoryByAntiqueId(id){ inventory->
            inventory.files.removeIf {
                it.id == fileId
            }
        }
    }

    fun addFileForAntiqueInventory(id : Long,file : MultipartFile,fileName : String){

        modInventoryByAntiqueId(id){
            val fileEntity = fileRepo.save(File(null,fileName))
            val fileId = contentService.saveContent(file,fileEntity.id!!)
            it.files.add(fileEntity)
        }
    }
}