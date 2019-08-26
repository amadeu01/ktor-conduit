/* Licensed under MIT */
package dev.amadeu

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*
import io.ktor.swagger.experimental.*
import io.ktor.auth.*
import io.ktor.http.*

/**
 * Conduit API
 *
 * Conduit API
 */
class ConduitAPIServer(val myjwt: MyJWT) {
    /**
     * User and Authentication
     */
    fun Routing.registerUserAndAuthentication() {
        post("/users/login") {
            val body = call.getBodyParam<LoginUserRequest>("body")

            if (false) httpException(HttpStatusCode.Unauthorized)
            if (false) httpException(422, "Unexpected error")

            val username = body.user.email
            // @TODO: Your username/password validation here
            val password = body.user.password
            if (username != password) httpException(HttpStatusCode.Unauthorized, "username != password")
            val token = myjwt.sign(username)
            call.respond(UserResponse(
                user = User(
                    email = "user.email",
                    token = token,
                    username = "user.username",
                    bio = "user.bio",
                    image = "user.image"
                )
            ))
        }

        post("/users") {
            val body = call.getBodyParam<NewUserRequest>("body")

            if (false) httpException(HttpStatusCode.Created)
            if (false) httpException(422, "Unexpected error")

            call.respond("")
        }

        authenticate("Token") {
            get("/users") {
                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(UserResponse(
                    user = User(
                        email = "user.email",
                        token = "user.token",
                        username = "user.username",
                        bio = "user.bio",
                        image = "user.image"
                    )
                ))
            }
        }

        authenticate("Token") {
            put("/users") {
                val body = call.getBodyParam<UpdateUserRequest>("body")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(UserResponse(
                    user = User(
                        email = "user.email",
                        token = "user.token",
                        username = "user.username",
                        bio = "user.bio",
                        image = "user.image"
                    )
                ))
            }
        }
    }

    /**
     * Profile
     */
    fun Routing.registerProfile() {
        get("/profiles/{username}") {
            val username = call.getPath<String>("username")

            if (false) httpException(HttpStatusCode.Unauthorized)
            if (false) httpException(422, "Unexpected error")

            call.respond(ProfileResponse(
                profile = Profile(
                    username = "profile.username",
                    bio = "profile.bio",
                    image = "profile.image",
                    following = false
                )
            ))
        }

        authenticate("Token") {
            post("/profiles/{username}/follow") {
                val username = call.getPath<String>("username")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(ProfileResponse(
                    profile = Profile(
                        username = "profile.username",
                        bio = "profile.bio",
                        image = "profile.image",
                        following = false
                    )
                ))
            }
        }

        authenticate("Token") {
            delete("/profiles/{username}/follow") {
                val username = call.getPath<String>("username")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(ProfileResponse(
                    profile = Profile(
                        username = "profile.username",
                        bio = "profile.bio",
                        image = "profile.image",
                        following = false
                    )
                ))
            }
        }
    }

    /**
     * Articles
     */
    fun Routing.registerArticles() {
        authenticate("Token") {
            get("/articles/feed") {
                val limit = call.getQuery<Int>("limit") { 20 }
                val offset = call.getQuery<Int>("offset") { 0 }

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(MultipleArticlesResponse(
                    articles = listOf(),
                    articlesCount = 0
                ))
            }
        }

        get("/articles") {
            val tag = call.getQuery<String>("tag") { "" }
            val author = call.getQuery<String>("author") { "" }
            val favorited = call.getQuery<String>("favorited") { "" }
            val limit = call.getQuery<Int>("limit") { 20 }
            val offset = call.getQuery<Int>("offset") { 0 }

            if (false) httpException(HttpStatusCode.Unauthorized)
            if (false) httpException(422, "Unexpected error")

            call.respond(MultipleArticlesResponse(
                articles = listOf(),
                articlesCount = 0
            ))
        }

        authenticate("Token") {
            post("/articles") {
                val article = call.getBodyParam<NewArticleRequest>("article")

                if (false) httpException(HttpStatusCode.Created)
                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond("")
            }
        }

        get("/articles/{slug}") {
            val slug = call.getPath<String>("slug")

            if (false) httpException(422, "Unexpected error")

            call.respond(SingleArticleResponse(
                article = Article(
                    slug = "article.slug",
                    title = "article.title",
                    description = "article.description",
                    body = "article.body",
                    tagList = listOf(),
                    createdAt = Date(),
                    updatedAt = Date(),
                    favorited = false,
                    favoritesCount = 0,
                    author = Profile(
                        username = "article.author.username",
                        bio = "article.author.bio",
                        image = "article.author.image",
                        following = false
                    )
                )
            ))
        }

        authenticate("Token") {
            put("/articles/{slug}") {
                val slug = call.getPath<String>("slug")
                val article = call.getBodyParam<UpdateArticleRequest>("article")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(SingleArticleResponse(
                    article = Article(
                        slug = "article.slug",
                        title = "article.title",
                        description = "article.description",
                        body = "article.body",
                        tagList = listOf(),
                        createdAt = Date(),
                        updatedAt = Date(),
                        favorited = false,
                        favoritesCount = 0,
                        author = Profile(
                            username = "article.author.username",
                            bio = "article.author.bio",
                            image = "article.author.image",
                            following = false
                        )
                    )
                ))
            }
        }

        authenticate("Token") {
            delete("/articles/{slug}") {
                val slug = call.getPath<String>("slug")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(Unit)
            }
        }
    }

    /**
     * Comments
     */
    fun Routing.registerComments() {
        get("/articles/{slug}/comments") {
            val slug = call.getPath<String>("slug")

            if (false) httpException(HttpStatusCode.Unauthorized)
            if (false) httpException(422, "Unexpected error")

            call.respond(MultipleCommentsResponse(
                comments = listOf()
            ))
        }

        authenticate("Token") {
            post("/articles/{slug}/comments") {
                val slug = call.getPath<String>("slug")
                val comment = call.getBodyParam<NewCommentRequest>("comment")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(SingleCommentResponse(
                    comment = Comment(
                        id = 0,
                        createdAt = Date(),
                        updatedAt = Date(),
                        body = "comment.body",
                        author = Profile(
                            username = "comment.author.username",
                            bio = "comment.author.bio",
                            image = "comment.author.image",
                            following = false
                        )
                    )
                ))
            }
        }

        authenticate("Token") {
            delete("/articles/{slug}/comments/{id}") {
                val slug = call.getPath<String>("slug")
                val id = call.getPath<Int>("id")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(Unit)
            }
        }
    }

    /**
     * Favorites
     */
    fun Routing.registerFavorites() {
        authenticate("Token") {
            post("/articles/{slug}/favorite") {
                val slug = call.getPath<String>("slug")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(SingleArticleResponse(
                    article = Article(
                        slug = "article.slug",
                        title = "article.title",
                        description = "article.description",
                        body = "article.body",
                        tagList = listOf(),
                        createdAt = Date(),
                        updatedAt = Date(),
                        favorited = false,
                        favoritesCount = 0,
                        author = Profile(
                            username = "article.author.username",
                            bio = "article.author.bio",
                            image = "article.author.image",
                            following = false
                        )
                    )
                ))
            }
        }

        authenticate("Token") {
            delete("/articles/{slug}/favorite") {
                val slug = call.getPath<String>("slug")

                if (false) httpException(HttpStatusCode.Unauthorized)
                if (false) httpException(422, "Unexpected error")

                call.respond(SingleArticleResponse(
                    article = Article(
                        slug = "article.slug",
                        title = "article.title",
                        description = "article.description",
                        body = "article.body",
                        tagList = listOf(),
                        createdAt = Date(),
                        updatedAt = Date(),
                        favorited = false,
                        favoritesCount = 0,
                        author = Profile(
                            username = "article.author.username",
                            bio = "article.author.bio",
                            image = "article.author.image",
                            following = false
                        )
                    )
                ))
            }
        }
    }
}
