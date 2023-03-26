package com.prac.demo.common.entity.item

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie: Item() {

    @Column(name = "director", nullable = true)
    var director: String? = null

    @Column(name = "actor", nullable = true)
    var actor: String? = null
}