package com.conexionDataBase.dao

import com.conexionDataBase.entity.BookEntity
import com.conexionDataBase.output.Console
import java.awt.print.Book
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class BookDao(private val console: Console, private val dataSource: DataSource):IBookDao {

    override fun insert(book: BookEntity): BookEntity? {
        val sql = "INSERT INTO book (id, name) VALUES (?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.id.toString())
                    stmt.setString(2, book.name)
                    val rs = stmt.executeUpdate()
                    if (rs ==1){
                        book
                    } else {
                        console.showMessage("Error, insert query failed ($rs records inserted)")
                        null
                    }

                }
            }
        }catch (e: SQLException){
            console.showMessage("Error, ${e.message}")
            null
        }
    }


    override fun selectById(id: UUID): BookEntity? {
        val sql = "SELECT * FROM book WHERE id = ?"
        return try {

            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        BookEntity(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                        )
                    }
                    else {
                        null
                    }
                }
            }
        } catch (e:SQLException){
            console.showMessage("Error ${e.message}")
            null
        }
    }

    override fun selectAll(): List<BookEntity>? {
        val sql = "SELECT * FROM book"
        return try {


            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<BookEntity>()
                    while (rs.next()) {
                        users.add(
                            BookEntity(
                                id = UUID.fromString(rs.getString("id")),
                                name = rs.getString("name")
                            )
                        )
                    }
                    users
                }
            }
        }catch (e:SQLException){
            console.showMessage("Error: ${e.message}")
            null
        }
    }

    override fun update(book: BookEntity): BookEntity? {
        val sql = "UPDATE book SET name = ? WHERE id = ?"
        return try {


            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.name)
                    stmt.setString(2, book.id.toString()) //un uuid pasado a string
                    val rs = stmt.executeUpdate()

                    if (rs ==1) {
                        book
                    } else{
                        console.showMessage("Error, update query failed ($rs) rows updated")
                        null
                    }
                }
            }
        }catch (e:SQLException){
            console.showMessage("Error, ${e.message}")
            null
        }
    }

    override fun deleteById(id: UUID):Boolean {
        val sql = "DELETE FROM book WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())

                    (stmt.executeUpdate() == 1)

                }
            }
        } catch (e: SQLException) {
            false
        }
    }


}