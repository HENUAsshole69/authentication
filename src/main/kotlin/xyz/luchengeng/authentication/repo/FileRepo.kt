package xyz.luchengeng.authentication.repo

import org.springframework.data.jpa.repository.JpaRepository
import xyz.luchengeng.authentication.entity.File
import java.util.*

interface FileRepo : JpaRepository<File,UUID> {
}