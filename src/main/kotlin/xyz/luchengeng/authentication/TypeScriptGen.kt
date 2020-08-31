package xyz.luchengeng.authentication

import me.ntrrgc.tsGenerator.TypeScriptGenerator
import xyz.luchengeng.authentication.entity.File
import xyz.luchengeng.authentication.entity.InventoryDto
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun main(){
    println(TypeScriptGenerator(
            rootClasses = setOf(
                    InventoryDto::class,
                    File::class
            ),
            mappings = mapOf(
                    LocalDateTime::class to "String",
                    LocalDate::class to "String",
                    Date::class to "String",
                    UUID::class to "String"
            )
    ).definitionsText)
}