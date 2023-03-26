package com.prac.demo.common.entity.item

import com.prac.demo.common.entity.CategoryItem
import com.prac.demo.common.exception.NotEnoughStockException
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    open var id: Long? = null

    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Column(name = "price", nullable = false)
    open var price: Int? = null

    @Column(name = "stock_quantity", nullable = false)
    open var stockQuantity: Int? = null

    @OneToMany(mappedBy = "item")
    open var categoryItemList: MutableList<CategoryItem> = mutableListOf()

    fun addStock(quantity: Int) {
        this.stockQuantity = this.stockQuantity?.plus(quantity)
    }

    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity?.minus(quantity)
        if (restStock!! < 0) {
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }
}