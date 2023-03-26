package com.prac.demo.common.service

import com.prac.demo.common.entity.item.Item
import com.prac.demo.common.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun save(item: Item) {
        itemRepository.save(item)
    }

    fun findOne(id: Long): Item {
        return itemRepository.findOne(id)
    }

    fun findAll(): List<Item> {
        return itemRepository.findAll()
    }
}