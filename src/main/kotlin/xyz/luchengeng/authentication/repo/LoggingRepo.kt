package xyz.luchengeng.authentication.repo

import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import xyz.luchengeng.authentication.entity.LoggingEntry
import xyz.luchengeng.authentication.entity.LoggingEntryDto

@Repository
interface LoggingRepo : JpaRepository<LoggingEntry,Long>