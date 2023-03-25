package com.prac.demo.common.entity.item

import jakarta.persistence.*

@Entity
@DiscriminatorValue("B")
class Book: Item() {

    @Column(name = "author", nullable = false)
    var author: String? = null

    @Column(name = "isbn", nullable = false)
    var isbn: String? = null
}