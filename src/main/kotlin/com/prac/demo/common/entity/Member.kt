package com.prac.demo.common.entity

import jakarta.persistence.*

@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    var id: Long? = null

    @Column
    var name: String? = null

    @Embedded
    var address: Address? = null

    @OneToMany(mappedBy = "member") // mappedBy = "member" : Order 테이블의 member 필드를 참조
    var orders: MutableList<Order>? = mutableListOf()
}