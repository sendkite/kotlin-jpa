package com.prac.demo.common.entity.item

import com.prac.demo.common.entity.CategoryItem
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "price", nullable = false)
    var price: Int? = null

    @Column(name = "stock_quantity", nullable = false)
    var stockQuantity: Int? = null

    @OneToMany(mappedBy = "item")
    var categoryItemList: MutableList<CategoryItem> = mutableListOf()
}