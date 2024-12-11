package com.example.mgokcafeteria.database

import org.jetbrains.exposed.sql.Table

object Categories : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    override val primaryKey = PrimaryKey(id)
}

object MenuItems : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val proteins = decimal("proteins", 10, 2).nullable()
    val fats = decimal("fats", 10, 2).nullable()
    val carbohydrates = decimal("carbohydrates", 10, 2).nullable()
    val calories = decimal("calories", 10, 2).nullable()
    val weight = decimal("weight", 10, 2).nullable()
    val ingredients = text("ingredients").nullable()
    val price = decimal("price", 10, 2).nullable()
    val categoryId = integer("category_id").references(Categories.id)
    val imagePath = text("image_path").nullable()
    override val primaryKey = PrimaryKey(id)
}
