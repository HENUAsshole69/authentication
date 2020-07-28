package xyz.luchengeng.authentication

import me.ntrrgc.tsGenerator.TypeScriptGenerator
import org.springframework.data.domain.AbstractPageRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import xyz.luchengeng.authentication.entity.*

class TypeScriptModelGen
    fun gen(){
        print(TypeScriptGenerator(rootClasses = setOf(
                Page::class
        )).definitionsText)
    }