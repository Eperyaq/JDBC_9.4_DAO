package com.conexionDataBase.service

import com.conexionDataBase.dao.BookDao
import com.conexionDataBase.entity.BookEntity
import java.util.*

class BookService(val bookDao: BookDao): IBookService {
    override fun insert(book: BookEntity): BookEntity? {
        return bookDao.insert(book)
    }

    override fun update(book: BookEntity): BookEntity? {
        return bookDao.update(book)
    }

    override fun deleteById(id: UUID): Boolean {
        return bookDao.deleteById(id)
    }

    override fun selectById(id: UUID): BookEntity? {
        return bookDao.selectById(id)
    }

    override fun selectAll(): List<BookEntity>? {
        return bookDao.selectAll()
    }
}