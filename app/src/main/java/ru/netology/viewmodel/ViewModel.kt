package ru.netology.viewmodel

import androidx.lifecycle.ViewModel
import repository.PostRepository
import repository.PostRepositoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()

    val data = repository.getAll()

    fun likeById(id: Long) = repository.likeById(id)

    fun repostById(id: Long) = repository.repostById(id)
}

fun getRightNumber(number: Int): String =
    when (number) {
        in (0..999) -> number.toString()
        in (1000..9999) -> String.format(
            "%.1f K",
            (number - number % 100) / 1000.0
        )
        in (10_000..999_999) -> "${number / 1000} K"
        in (1000000..Int.MAX_VALUE) -> String.format(
            "%.1f M",
            (number - number % 100_000) / 1_000_000.0
        )
        else -> throw RuntimeException()
    }