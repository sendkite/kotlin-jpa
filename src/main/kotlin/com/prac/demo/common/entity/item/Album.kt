package com.prac.demo.common.entity.item

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album: Item() {

    @Column(name = "artist", nullable = false)
    var artist: String? = null

    @Column(name = "etc", nullable = false)
    var etc: String? = null
}