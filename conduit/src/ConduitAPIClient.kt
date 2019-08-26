/* Licensed under MIT */
package dev.amadeu

import io.ktor.client.*
import io.ktor.client.request.*

/**
 * Conduit API Client
 *
 * Conduit API
 */
open class ConduitAPIClient(val endpoint: String, val client: HttpClient = HttpClient()) {
    /**
     * Existing user login
     *
     * Login for existing user
     *
     * @param body Credentials to use
     *
     * @return OK
     */
    suspend fun login(
        body: LoginUserRequest // BODY
    ): UserResponse {
        return client.post("$endpoint/users/login") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Register a new user
     *
     * Register a new user
     *
     * @param body Details of the new user to register
     *
     * @return OK
     */
    suspend fun createUser(
        body: NewUserRequest // BODY
    ): String {
        return client.post<String>("$endpoint/users") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Get current user
     *
     * Gets the currently logged-in user
     *
     * @return OK
     */
    suspend fun getCurrentUser(): UserResponse {
        return client.get<UserResponse>("$endpoint/users") {
        }
    }

    /**
     * Update current user
     *
     * Updated user information for current user
     *
     * @param body User details to update. At least **one** field is required.
     *
     * @return OK
     */
    suspend fun updateCurrentUser(
        body: UpdateUserRequest // BODY
    ): UserResponse {
        return client.put<UserResponse>("$endpoint/users") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Get a profile
     *
     * Get a profile of a user of the system. Auth is optional
     *
     * @param username Username of the profile to get
     *
     * @return OK
     */
    suspend fun getProfileByUsername(
        username: String // PATH
    ): ProfileResponse {
        return client.get<ProfileResponse>("$endpoint/profiles/$username") {
        }
    }

    /**
     * Follow a user
     *
     * Follow a user by username
     *
     * @param username Username of the profile you want to follow
     *
     * @return OK
     */
    suspend fun followUserByUsername(
        username: String // PATH
    ): ProfileResponse {
        return client.post<ProfileResponse>("$endpoint/profiles/$username/follow") {
        }
    }

    /**
     * Unfollow a user
     *
     * Unfollow a user by username
     *
     * @param username Username of the profile you want to unfollow
     *
     * @return OK
     */
    suspend fun unfollowUserByUsername(
        username: String // PATH
    ): ProfileResponse {
        return client.delete<ProfileResponse>("$endpoint/profiles/$username/follow") {
        }
    }

    /**
     * Get recent articles from users you follow
     *
     * Get most recent articles from users you follow. Use query parameters to limit. Auth is required
     *
     * @param limit Limit number of articles returned (default is 20)
     * @param offset Offset/skip number of articles (default is 0)
     *
     * @return OK
     */
    suspend fun getArticlesFeed(
        limit: Int = 20, // QUERY
        offset: Int = 0 // QUERY
    ): MultipleArticlesResponse {
        return client.get<MultipleArticlesResponse>("$endpoint/articles/feed") {
            this.url {
                this.parameters.apply {
                    this.append("limit", "$limit")
                    this.append("offset", "$offset")
                }
            }
        }
    }

    /**
     * Get recent articles globally
     *
     * Get most recent articles globally. Use query parameters to filter results. Auth is optional
     *
     * @param tag Filter by tag
     * @param author Filter by author (username)
     * @param favorited Filter by favorites of a user (username)
     * @param limit Limit number of articles returned (default is 20)
     * @param offset Offset/skip number of articles (default is 0)
     *
     * @return OK
     */
    suspend fun getArticles(
        tag: String = "", // QUERY
        author: String = "", // QUERY
        favorited: String = "", // QUERY
        limit: Int = 20, // QUERY
        offset: Int = 0 // QUERY
    ): MultipleArticlesResponse {
        return client.get<MultipleArticlesResponse>("$endpoint/articles") {
            this.url {
                this.parameters.apply {
                    this.append("tag", "$tag")
                    this.append("author", "$author")
                    this.append("favorited", "$favorited")
                    this.append("limit", "$limit")
                    this.append("offset", "$offset")
                }
            }
        }
    }

    /**
     * Create an article
     *
     * Create an article. Auth is required
     *
     * @param article Article to create
     *
     * @return OK
     */
    suspend fun createArticle(
        article: NewArticleRequest // BODY
    ): String {
        return client.post<String>("$endpoint/articles") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["article"] = article
            }
        }
    }

    /**
     * Get an article
     *
     * Get an article. Auth not required
     *
     * @param slug Slug of the article to get
     *
     * @return OK
     */
    suspend fun getArticle(
        slug: String // PATH
    ): SingleArticleResponse {
        return client.get<SingleArticleResponse>("$endpoint/articles/$slug") {
        }
    }

    /**
     * Update an article
     *
     * Update an article. Auth is required
     *
     * @param slug Slug of the article to update
     * @param article Article to update
     *
     * @return OK
     */
    suspend fun updateArticle(
        slug: String, // PATH
        article: UpdateArticleRequest // BODY
    ): SingleArticleResponse {
        return client.put<SingleArticleResponse>("$endpoint/articles/$slug") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["article"] = article
            }
        }
    }

    /**
     * Delete an article
     *
     * Delete an article. Auth is required
     *
     * @param slug Slug of the article to delete
     *
     * @return OK
     */
    suspend fun deleteArticle(
        slug: String // PATH
    ) {
        return client.delete<Unit>("$endpoint/articles/$slug") {
        }
    }

    /**
     * Get comments for an article
     *
     * Get the comments for an article. Auth is optional
     *
     * @param slug Slug of the article that you want to get comments for
     *
     * @return OK
     */
    suspend fun getArticleComments(
        slug: String // PATH
    ): MultipleCommentsResponse {
        return client.get<MultipleCommentsResponse>("$endpoint/articles/$slug/comments") {
        }
    }

    /**
     * Create a comment for an article
     *
     * Create a comment for an article. Auth is required
     *
     * @param slug Slug of the article that you want to create a comment for
     * @param comment Comment you want to create
     *
     * @return OK
     */
    suspend fun createArticleComment(
        slug: String, // PATH
        comment: NewCommentRequest // BODY
    ): SingleCommentResponse {
        return client.post<SingleCommentResponse>("$endpoint/articles/$slug/comments") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["comment"] = comment
            }
        }
    }

    /**
     * Delete a comment for an article
     *
     * Delete a comment for an article. Auth is required
     *
     * @param slug Slug of the article that you want to delete a comment for
     * @param id ID of the comment you want to delete
     *
     * @return OK
     */
    suspend fun deleteArticleComment(
        slug: String, // PATH
        id: Int // PATH
    ) {
        return client.delete<Unit>("$endpoint/articles/$slug/comments/$id") {
        }
    }

    /**
     * Favorite an article
     *
     * Favorite an article. Auth is required
     *
     * @param slug Slug of the article that you want to favorite
     *
     * @return OK
     */
    suspend fun createArticleFavorite(
        slug: String // PATH
    ): SingleArticleResponse {
        return client.post<SingleArticleResponse>("$endpoint/articles/$slug/favorite") {
        }
    }

    /**
     * Unfavorite an article
     *
     * Unfavorite an article. Auth is required
     *
     * @param slug Slug of the article that you want to unfavorite
     *
     * @return OK
     */
    suspend fun deleteArticleFavorite(
        slug: String // PATH
    ): SingleArticleResponse {
        return client.delete<SingleArticleResponse>("$endpoint/articles/$slug/favorite") {
        }
    }

    /**
     * Get tags
     *
     * Get tags. Auth not required
     *
     * @return OK
     */
    suspend fun getTags(): TagsResponse {
        return client.get<TagsResponse>("$endpoint/tags") {
        }
    }
}
