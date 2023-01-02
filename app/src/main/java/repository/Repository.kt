package repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.data.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun repostById(id: Long)
}

class PostRepositoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            1,
            "Нетология. Университет интернет-профессий",
            "today at 19:10",
            10,
            679,
            1999,
            text = "Всем привет, это новая Нетология!"
        ), Post(
            2,
            "Нетология. Университет интернет-профессий",
            "today at 19:10",
            10,
            679,
            1999,
            text = "Сегодня на повестке дня у нас очень много букоф так что они вряд ли уместятся в одну строчку"
        ), Post(
            3,
            "Нетология. Университет интернет-профессий",
            "today at 19:10",
            10,
            679,
            1999,
            text = "А может быть, для этого понадобится даже гораздо больше места. " +
                    "Опубликуйте изменения в вашем проекте на GitHub. Убедитесь, что apk собирается с помощью GitHub Actions и при" +
                    " установке в эмуляторе приложение работает корректно."
        )
    )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else {
                if (it.isLiked) {
                    it.copy(isLiked = !it.isLiked, likes = --it.likes)
                } else {
                    it.copy(isLiked = !it.isLiked, likes = ++it.likes)
                }
            }
        }
        data.value = posts
    }

    override fun repostById(id: Long) {
        posts = posts.map { if (it.id != id) it else it.copy(reposts = ++it.reposts) }
        data.value = posts
    }
}