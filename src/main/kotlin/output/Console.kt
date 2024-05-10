package com.conexionDataBase.output

import com.conexionDataBase.entity.BookEntity

class Console: IOutputInfo {

    override fun showMessage(message: String, lineBreak:Boolean){
        if (lineBreak){
            println(message)
        } else {
            print(message)
        }
    }

    override fun show(userList:List<BookEntity>?, message: String){
        if (userList != null) {
            if (userList.isEmpty()) {
                showMessage("No users found")
            } else {
                userList.forEachIndexed { index, user ->
                    showMessage("\t ${index + 1}, $user")
                }
            }
        }
    }

}