package com.prac.demo.common.repository

import com.prac.demo.common.entity.Order
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun save(order: Order) {
        if (order.id == null) {
            em.persist(order)
        } else {
            em.merge(order)
        }
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAll(): List<Order> {
        return em.createQuery("select o from Order o", Order::class.java)
            .resultList
    }
}