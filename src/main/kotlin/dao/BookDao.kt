package com.conexionDataBase.dao

import com.conexionDataBase.entity.BookEntity
import com.conexionDataBase.output.Console
import java.sql.SQLException
import javax.sql.DataSource

class BookDao(private val console: Console, private val dataSource: DataSource):IBookDao {

    override fun insert(user: BookEntity): BookEntity? {
        val sql = "INSERT INTO tuser (id, name, email) VALUES (?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.id.toString())
                    stmt.setString(2, user.name)
                    val rs = stmt.executeUpdate()
                    if (rs ==1){
                        user
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


    override fun getById(id: UUID): UserEntity? {
        val sql = "SELECT * FROM tuser WHERE id = ?"
        return try {

            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        UserEntity(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            email = rs.getString("email")
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

    override fun getAll(): List<UserEntity>? {
        val sql = "SELECT * FROM tuser"
        return try {


            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<UserEntity>()
                    while (rs.next()) {
                        users.add(
                            UserEntity(
                                id = UUID.fromString(rs.getString("id")),
                                name = rs.getString("name"),
                                email = rs.getString("email")
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

    override fun update(user: UserEntity):UserEntity? {
        val sql = "UPDATE tuser SET name = ?, email = ? WHERE id = ?"
        return try {


            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.name)
                    stmt.setString(2, user.email)
                    stmt.setString(3, user.id.toString())
                    val rs = stmt.executeUpdate()

                    if (rs ==1) {
                        user
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

    override fun delete(id: UUID):Boolean {
        val sql = "DELETE FROM tuser WHERE id = ?"
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