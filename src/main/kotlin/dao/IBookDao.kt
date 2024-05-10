package com.conexionDataBase.dao

import com.conexionDataBase.entity.BookEntity
import java.util.UUID

interface IBookDao {
    fun insert(book:BookEntity): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(id: UUID): Boolean
    fun selectById(id: UUID): BookEntity?
    fun selectAll(): List<BookEntity?>

}