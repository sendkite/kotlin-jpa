package com.prac.demo.common.entity.item

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie: Item() {

    @Column(name = "director", nullable = false)
    var director: String? = null

    @Column(name = "actor", nullable = false)
    var actor: String? = null
}