package xyz.luchengeng.authentication.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Inventory (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val requisitionTime : LocalDateTime,
        val requisitionStatus: String,
        val text: String,
        @OneToMany(cascade = [CascadeType.ALL]) val files: MutableList<File>,
        @OneToOne val antique: Antique
){
    fun toDto() : InventoryDto{
        return InventoryDto(this.requisitionTime,this.requisitionStatus,this.text)
    }
}

data class InventoryDto(
                        val requisitionTime : LocalDateTime,
                        val requisitionStatus: String,
                        val text: String
                        ){
    fun toEntity(antique: Antique) : Inventory{
        return Inventory(null, LocalDateTime.now(),this.requisitionStatus, text,mutableListOf(),antique)
    }
}