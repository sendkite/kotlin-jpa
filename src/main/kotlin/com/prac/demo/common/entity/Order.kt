package com.prac.demo.common.entity

import com.prac.demo.common.enum.DeliveryStatus
import com.prac.demo.common.enum.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem>? = mutableListOf()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null

    @Column(name = "order_date", nullable = false)
    var orderDate: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = null

    // 생성 메서드
    fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
        val order = Order().apply {
            this.orderDate = LocalDateTime.now()
            this.status = OrderStatus.ORDER
            this.member = member.apply { this.orders?.add(this@Order) }
            this.delivery = delivery.apply { this.order = this@Order }
        }
        for (orderItem in orderItems) {
            order.addOrderItem(orderItem)
        }
        return order
    }

    // 비즈니스 로직
    fun cancel() {
        if (delivery?.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }
        this.status = OrderStatus.CANCEL
        for (orderItem in orderItems!!) {
            orderItem.cancel()
        }
    }

    // 조회 로직
    fun getTotalPrice(): Int {
        return orderItems!!.sumOf { it.getTotalPrice() }
    }

    // 양방향 일때 연관관계 편의 메서드
    fun addOrderItem(orderItem: OrderItem) {
        orderItems?.add(orderItem)
        orderItem.order = this
    }
}