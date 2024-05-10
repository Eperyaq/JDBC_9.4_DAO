package com.conexionDataBase

import com.conexionDataBase.dao.BookDao
import com.conexionDataBase.db_connection.DataSourceFactory
import com.conexionDataBase.entity.BookEntity
import com.conexionDataBase.output.Console
import com.conexionDataBase.service.BookService

fun main() {
// Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val console = Console()

    // Creamos la instancia de UserDAO
    val userDao = BookDao(console, dataSource)

    // Creamos la instancia de UserService
    val userService = BookService(userDao)

    // Creamos un nuevo usuario
    val newUser = UserEntity(name = "John Doe", email = "johndoe@example.com")
    var createdUser = userService.create(newUser)
    console.showMessage("Created user: $createdUser")

    // Obtenemos un usuario por su ID

    val foundUser =
        if (createdUser != null){
            userService.getById(createdUser.id)
        }else{
            null
        }
    console.showMessage("Found user: $foundUser")

    // Actualizamos el usuario
    val updatedUser = foundUser?.copy(name = "Jane Doe")


    val savedUser =
        if (updatedUser != null){
            userService.update(updatedUser)
        } else {
            null
        }
    console.showMessage("Updated user: ${savedUser} ")

    val otherUser = BookEntity(name = "Pepito grillo")
    createdUser = userService.create( otherUser)
    console.showMessage("Created book: $createdUser")


    // Obtenemos todos los usuarios
    var allUsers = userService.getAll()

    console.show(allUsers)


    // Eliminamos el usuario
    if (savedUser != null){
        userService.delete(savedUser.id)
        console.showMessage("User deleted")
    }else{
        console.showMessage("user not deleted")
    }


    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    console.showMessage("All users: $allUsers")

    // Eliminamos el usuario
    userService.delete(otherUser.id)
    console.showMessage("User deleted")
    console.show(allUsers)
}