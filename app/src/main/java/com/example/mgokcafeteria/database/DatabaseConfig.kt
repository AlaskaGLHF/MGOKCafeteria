package com.example.mgokcafeteria.database

import com.example.mgokcafeteria.models.Category
import com.example.mgokcafeteria.models.MenuItem
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

object DatabaseConfig {
    // Подключение к локальной базе данных PostgreSQL
    fun connect(): Connection {
        val url = "jdbc:postgresql://109.120.134.140:5432/MgokMenu"  // Адрес вашей БД
        val user = "postgres"  // Имя пользователя базы данных
        val password = "yNx-tKr-zA9-kEZ"  // Пароль для доступа

        return DriverManager.getConnection(url, user, password)
    }

    // Получение списка категорий
    fun getCategories(): List<Category> {
        val connection = connect()
        val statement: Statement = connection.createStatement()
        val resultSet: ResultSet = statement.executeQuery("SELECT * FROM categories")

        val categories = mutableListOf<Category>()
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val name = resultSet.getString("name")
            categories.add(Category(id, name))
        }
        connection.close() // Закрытие соединения

        return categories
    }

    // Получение списка блюд
    fun getMenuItems(): List<MenuItem> {
        val connection = connect()
        val statement: Statement = connection.createStatement()
        val resultSet: ResultSet = statement.executeQuery("SELECT * FROM menu_items")

        val menuItems = mutableListOf<MenuItem>()
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val name = resultSet.getString("name")
            val proteins = resultSet.getDouble("proteins")
            val fats = resultSet.getDouble("fats")
            val carbohydrates = resultSet.getDouble("carbohydrates")
            val calories = resultSet.getDouble("calories")
            val weight = resultSet.getDouble("weight")
            val ingredients = resultSet.getString("ingredients")
            val price = resultSet.getDouble("price")
            val categoryId = resultSet.getInt("category_id")
            val imagePath = resultSet.getString("image_path")

            menuItems.add(MenuItem(id, name, proteins, fats, carbohydrates, calories, weight, ingredients, price, categoryId, imagePath))
        }
        connection.close()

        return menuItems
    }
}
