package com.sudo_pacman.asaxiybooks.data.model

data class BookData(
    var author: String,
    var bookUrl: String,
    var categoryId: String,
    var coverImage: String,
    var description: String,
    var filePath: String,
    var name: String,
    var totalSize: String,
    var type: String,
)