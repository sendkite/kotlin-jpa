package com.prac.demo.common.entity

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

//    // 양방향 일때 연관관계 편의 메서드
//    fun addOrderItem(orderItem: OrderItem) {
//        orderItems?.add(orderItem)
//        orderItem.order = this
//    }
//
//    fun setDelivery(delivery: Delivery) {
//        this.delivery = delivery
//        delivery.order = this
//    }
//
//    fun setMember(member: Member) {
//        this.member = member
//        member.orders?.add(this)
//    }
}