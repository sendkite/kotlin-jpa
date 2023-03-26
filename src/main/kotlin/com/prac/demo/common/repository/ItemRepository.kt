package com.prac.demo.common.repository

import com.prac.demo.common.entity.item.Item
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class ItemRepository {

    @PersistenceContext
    lateinit var em: EntityManager

    fun save(item: Item) {
        if (item.id == null) {
            em.persist(item)
        } else {
            em.merge(item)
        }
    }

    fun findOne(id: Long): Item {
        return em.find(Item::class.java, id)
    }

    fun findAll(): List<Item> {
        return em.createQuery("select i from Item i", Item::class.java)
            .resultList
    }

    fun findByName(name: String): List<Item> {
        return em.createQuery("select i from Item i where i.name = :name", Item::class.java)
            .setParameter("name", name)
            .resultList
    }

    fun update(item: Item) {
        em.find(Item::class.java, item.id).apply {
            name = item.name
            price = item.price
            stockQuantity = item.stockQuantity
        }
    }

    fun delete(item: Item) {
        em.remove(item)
    }
}