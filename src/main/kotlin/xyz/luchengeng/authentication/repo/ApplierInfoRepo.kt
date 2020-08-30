package xyz.luchengeng.authentication.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xyz.luchengeng.authentication.entity.ApplierInfo

@Repository
interface ApplierInfoRepo : JpaRepository<ApplierInfo,Long> {
}