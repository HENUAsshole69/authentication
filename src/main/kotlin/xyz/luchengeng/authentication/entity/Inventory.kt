package xyz.luchengeng.authentication.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Inventory (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long?,
        val requisitionTime : LocalDateTime,
        val requisitionStatus: String,
        @OneToMany(cascade = [CascadeType.ALL]) val files: MutableList<File>,
        @OneToOne val antique: Antique
){
    fun toDto() : InventoryDto{
        return InventoryDto(this.requisitionTime,this.requisitionStatus)
    }
}

data class InventoryDto(
                        val requisitionTime : LocalDateTime,
                        val requisitionStatus: String
                        ){
    fun toEntity(antique: Antique) : Inventory{
        return Inventory(null, LocalDateTime.now(),this.requisitionStatus, mutableListOf(),antique)
    }
}