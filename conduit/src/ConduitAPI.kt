/* Licensed under MIT */
package dev.amadeu

import java.util.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.swagger.experimental.*

data class UserResponse(
    val user: User
)

data class User(
    val email: String,
    val token: String,
    val username: String,
    val bio: String,
    val image: String
)

data class GenericErrorModel(
    val errors: DefinitionsGenericErrorModelErrors
)

data class LoginUserRequest(
    val user: LoginUser
)

data class LoginUser(
    val email: String,
    val password: String
)

data class NewUserRequest(
    val user: NewUser
)

data class NewUser(
    val username: String,
    val email: String,
    val password: String
)

data class UpdateUserRequest(
    val user: UpdateUser
)

data class UpdateUser(
    val email: String,
    val token: String,
    val username: String,
    val bio: String,
    val image: String
)

data class ProfileResponse(
    val profile: Profile
)

data class Profile(
    val username: String,
    val bio: String,
    val image: String,
    val following: Boolean
)

data class MultipleArticlesResponse(
    val articles: List<Article>,
    val articlesCount: Int
)

data class Article(
    val slug: String,
    val title: String,
    val description: String,
    val body: String,
    val tagList: List<String>,
    val createdAt: Date,
    val updatedAt: Date,
    val favorited: Boolean,
    val favoritesCount: Int,
    val author: Profile
)

data class SingleArticleResponse(
    val article: Article
)

data class NewArticleRequest(
    val article: NewArticle
)

data class NewArticle(
    val title: String,
    val description: String,
    val body: String,
    val tagList: List<String>
)

data class UpdateArticleRequest(
    val article: UpdateArticle
)

data class UpdateArticle(
    val title: String,
    val description: String,
    val body: String
)

data class MultipleCommentsResponse(
    val comments: List<Comment>
)

data class Comment(
    val id: Int,
    val createdAt: Date,
    val updatedAt: Date,
    val body: String,
    val author: Profile
)

data class SingleCommentResponse(
    val comment: Comment
)

data class NewCommentRequest(
    val comment: NewComment
)

data class NewComment(
    val body: String
)

data class TagsResponse(
    val tags: List<String>
)

// Synthetic class name
data class DefinitionsGenericErrorModelErrors(
    val body: List<String>
)
