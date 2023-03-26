package com.prac.demo.common.entity

import com.prac.demo.common.entity.item.Item
import jakarta.persistence.*

@Entity
class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null

    var orderPrice: Int? = null

    var count: Int? = null

    // 생성 메서드
    fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
        this.item = item
        this.orderPrice = orderPrice
        this.count = count
        item.removeStock(count)
        return this
    }

    // 비즈니스 로직
    fun cancel() {
        item?.addStock(count!!)
    }

    fun getTotalPrice(): Int {
        return orderPrice!! * count!!
    }
}