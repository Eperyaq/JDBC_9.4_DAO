package com.conexionDataBase.service

import com.conexionDataBase.entity.BookEntity
import java.util.*

interface IBookService {

    fun insert(book: BookEntity): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(id: UUID): Boolean
    fun selectById(id: UUID): BookEntity?
    fun selectAll(): List<BookEntity>?
}