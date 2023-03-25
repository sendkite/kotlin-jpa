package com.prac.demo.common.entity

import com.prac.demo.common.enum.DeliveryStatus
import jakarta.persistence.*

@Entity
class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", nullable = false)
    var id: Long? = null

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order? = null

    @Embedded
    var address: Address? = null

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus? = null
}