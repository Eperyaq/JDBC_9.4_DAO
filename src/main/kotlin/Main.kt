package com.conexionDataBase

import com.conexionDataBase.dao.BookDao
import com.conexionDataBase.db_connection.DataSourceFactory
import com.conexionDataBase.entity.BookEntity
import com.conexionDataBase.output.Console
import com.conexionDataBase.service.BookService

fun main() {
// Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.JDBC)

    val console = Console()

    // Creamos la instancia de UserDAO
    val bookDao = BookDao(console, dataSource)

    // Creamos la instancia de UserService
    val bookService = BookService(bookDao)

    // Creamos un nuevo usuario
    val newUser = BookEntity(name = "libro chulo")
    var createdUser = bookService.insert(newUser)
    console.showMessage("Created book: $createdUser")

    // Obtenemos un usuario por su ID

    val foundUser =
        if (createdUser != null){
            bookService.selectById(createdUser.id)
        }else{
            null
        }
    console.showMessage("book found: $foundUser")

    // Actualizamos el libro
    val updatedUser = foundUser?.copy(name = "LibroBomba")


    val savedUser =
        if (updatedUser != null){
            bookService.update(updatedUser)
        } else {
            null
        }
    console.showMessage("Updated user: ${savedUser} ")

    val otherUser = BookEntity(name = "Pepito grillo")
    createdUser = bookService.insert( otherUser)
    console.showMessage("Created book: $createdUser")


    // Obtenemos todos los usuarios
    var allUsers = bookService.selectAll()


    console.show(allUsers)


    // Eliminamos el usuario
    if (savedUser != null){
        bookService.deleteById(savedUser.id)
        console.showMessage("Book deleted")
    }else{
        console.showMessage("Book not deleted")
    }


    // Obtenemos todos los usuarios
    allUsers = bookService.selectAll()
    console.showMessage("All books: $allUsers")

    // Eliminamos el usuario
    bookService.deleteById(otherUser.id)
    console.showMessage("book deleted")
    console.show(allUsers)
}