package com.example.advancedtodolist

class Task {
    var title: String
    var category: String

    constructor(title: String, category: String) {
        this.title = title
        this.category = category
    }

    fun getTaskTitle(): String {
        return title
    }

    fun setTaskTitle(newTitle: String) {
        title = newTitle
    }

    fun getTaskCategory(): String {
        return category
    }

    fun setTaskCategory(newCategory: String) {
        category = newCategory
    }
}
