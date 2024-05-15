package com.conexionDataBase.dao

import com.conexionDataBase.entity.BookEntity
import java.util.*

class BookFile:IBookDao {
    override fun insert(book: BookEntity): BookEntity? {
        TODO("Not yet implemented")
    }

    override fun update(book: BookEntity): BookEntity? {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun selectById(id: UUID): BookEntity? {
        TODO("Not yet implemented")
    }

    override fun selectAll(): List<BookEntity>? {
        TODO("Not yet implemented")
    }
}