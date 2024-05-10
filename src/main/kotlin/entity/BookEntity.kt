package com.conexionDataBase.entity

import java.util.UUID

data class BookEntity(val id:UUID = UUID.randomUUID(), val name:String) {
}