package com.conexionDataBase.output

import com.conexionDataBase.entity.BookEntity

interface IOutputInfo {
    fun showMessage(message: String, lineBreak:Boolean = true)

    fun show(userList:List<BookEntity>?,message:String = "All users")

}