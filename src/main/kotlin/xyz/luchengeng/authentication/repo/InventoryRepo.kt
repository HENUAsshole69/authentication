package xyz.luchengeng.authentication.repo

import org.springframework.data.jpa.repository.JpaRepository
import xyz.luchengeng.authentication.entity.Inventory

interface InventoryRepo : JpaRepository<Inventory,Long> {
    fun findByAntiqueId(antiqueId: Long) : Inventory?
}