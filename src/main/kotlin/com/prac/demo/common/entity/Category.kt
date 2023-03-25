package com.prac.demo.common.entity

import com.prac.demo.common.entity.item.Item
import jakarta.persistence.*

@Entity
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = null

    @OneToMany(mappedBy = "parent")
    var child: MutableList<Category> = mutableListOf()

    @OneToMany(mappedBy = "category")
    var categoryItemList: MutableList<CategoryItem> = mutableListOf()

//    fun addChildCategory(category: Category) {
//        this.child.add(category)
//        category.parent = this
//    }
}