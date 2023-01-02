package com.example.firstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firstandroidapp.databinding.ActivityMainBinding
import com.example.firstandroidapp.databinding.PostCardBinding
import ru.netology.viewmodel.PostViewModel
import ru.netology.viewmodel.getRightNumber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.data.observe(this) { posts ->
            binding.container.removeAllViews()
            posts.map { post ->
                PostCardBinding.inflate(layoutInflater, binding.container, true).apply {
                    author.text = post.author
                    published.text = post.published
                    likesNumber.text = getRightNumber(post.likes)
                    repostsNumber.text = getRightNumber(post.reposts)
                    viewsNumber.text = getRightNumber(post.views)
                    paragraph1.text = post.text
                    like.setImageResource(
                        if (post.isLiked) R.drawable.liked else R.drawable.like
                    )
                    reposts.setOnClickListener {
                        viewModel.repostById(post.id)
                    }

                    like.setOnClickListener {
                        viewModel.likeById(post.id)
                    }
                }.root
            }
        }
    }
}