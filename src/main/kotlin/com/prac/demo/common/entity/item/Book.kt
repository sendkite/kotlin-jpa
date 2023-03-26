package com.prac.demo.common.entity.item

import jakarta.persistence.*

@Entity
@DiscriminatorValue("B")
class Book: Item() {

    @Column(name = "author", nullable = true)
    var author: String? = null

    @Column(name = "isbn", nullable = true)
    var isbn: String? = null
}