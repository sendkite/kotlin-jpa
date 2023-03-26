package com.prac.demo.common.service

import com.prac.demo.common.entity.item.Album
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    lateinit var itemService: ItemService
    @Autowired
    lateinit var em: EntityManager

    @Test
    fun `상품 등록`() {
        // given
        val item = Album().apply {
            name = "item1"
            price = 1000
            stockQuantity = 10
            artist = "artist1"
            etc = "etc1"
        }

        // when
        itemService.save(item)

        // then
        val findItem = itemService.findOne(item.id!!)
        assertEquals(item, findItem)
    }

    @Test
    fun `상품 목록 조회`() {
        // given
        val item1 = Album().apply {
            name = "item1"
            price = 1000
            stockQuantity = 10
            artist = "artist1"
            etc = "etc1"
        }
        val item2 = Album().apply {
            name = "item2"
            price = 2000
            stockQuantity = 20
            artist = "artist2"
            etc = "etc2"
        }
        val item3 = Album().apply {
            name = "item3"
            price = 3000
            stockQuantity = 30
            artist = "artist3"
            etc = "etc3"
        }
        itemService.save(item1)
        itemService.save(item2)
        itemService.save(item3)

        // when
        val findItems = itemService.findAll()

        // then
        assertEquals(3, findItems.size)
        assertEquals(item1, findItems[0])
        assertEquals(item2, findItems[1])
        assertEquals(item3, findItems[2])
    }

    @Test
    fun `상품 조회`() {
        // given
        val item = Album().apply {
            name = "item1"
            price = 1000
            stockQuantity = 10
            artist = "artist1"
            etc = "etc1"
        }
        itemService.save(item)

        // when
        val findItem = itemService.findOne(item.id!!)

        // then
        assertEquals(item, findItem)
    }
}