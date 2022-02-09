package com.example.jetnote.model

import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
