package xyz.luchengeng.authentication.entity

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

data class ApiPageRequest(val page: Int, val size : Int,val sort : ApiSort){
    fun toPageRequest() = PageRequest.of(page, size,sort.toSort())
}

data class ApiSort(val orders : List<ApiOrder>){
    fun toSort() = Sort.by(this.orders.map { it.toOrder() })
}

data class ApiOrder(
        val direction: Sort.Direction,
        val property: String,
        val nullHandling: Sort.NullHandling
){
    fun toOrder() = Sort.Order(direction, property,nullHandling)
}