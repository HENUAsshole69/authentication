package xyz.luchengeng.authentication

import me.ntrrgc.tsGenerator.TypeScriptGenerator
import xyz.luchengeng.authentication.entity.*

class TypeScriptModelGen
    fun gen(){
        print(TypeScriptGenerator(rootClasses = setOf(
                LoggingEntryDto::class
        )).definitionsText)
    }