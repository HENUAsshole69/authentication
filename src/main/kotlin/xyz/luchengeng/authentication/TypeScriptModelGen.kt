package xyz.luchengeng.authentication

import me.ntrrgc.tsGenerator.TypeScriptGenerator
import xyz.luchengeng.authentication.entity.*

class TypeScriptModelGen
    fun main(){
        print(TypeScriptGenerator(rootClasses = setOf(
                WearAndTear::class
        )).definitionsText)
    }