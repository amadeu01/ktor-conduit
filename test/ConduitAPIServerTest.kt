package dev.amadeu

import java.util.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.server.testing.*
import io.ktor.swagger.experimental.*
import kotlin.test.*

class SwaggerRoutesTest {
    /**
     * @see ConduitAPIServer.login
     */
    @Test
    fun testLogin() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/users/login") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.createUser
     */
    @Test
    fun testCreateUser() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/users") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getCurrentUser
     */
    @Test
    fun testGetCurrentUser() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/users") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.updateCurrentUser
     */
    @Test
    fun testUpdateCurrentUser() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Put, "/users") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getProfileByUsername
     */
    @Test
    fun testGetProfileByUsername() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/profiles/{username}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.followUserByUsername
     */
    @Test
    fun testFollowUserByUsername() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/profiles/{username}/follow") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.unfollowUserByUsername
     */
    @Test
    fun testUnfollowUserByUsername() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Delete, "/profiles/{username}/follow") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getArticlesFeed
     */
    @Test
    fun testGetArticlesFeed() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/articles/feed") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getArticles
     */
    @Test
    fun testGetArticles() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/articles") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.createArticle
     */
    @Test
    fun testCreateArticle() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/articles") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getArticle
     */
    @Test
    fun testGetArticle() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/articles/{slug}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.updateArticle
     */
    @Test
    fun testUpdateArticle() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Put, "/articles/{slug}") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.deleteArticle
     */
    @Test
    fun testDeleteArticle() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Delete, "/articles/{slug}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getArticleComments
     */
    @Test
    fun testGetArticleComments() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/articles/{slug}/comments") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.createArticleComment
     */
    @Test
    fun testCreateArticleComment() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/articles/{slug}/comments") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.deleteArticleComment
     */
    @Test
    fun testDeleteArticleComment() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Delete, "/articles/{slug}/comments/{id}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.createArticleFavorite
     */
    @Test
    fun testCreateArticleFavorite() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/articles/{slug}/favorite") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.deleteArticleFavorite
     */
    @Test
    fun testDeleteArticleFavorite() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Delete, "/articles/{slug}/favorite") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see ConduitAPIServer.getTags
     */
    @Test
    fun testGetTags() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/tags") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    fun <R> withTestApplication(test: TestApplicationEngine.() -> R): R {
        return withApplication(createTestEnvironment()) {
            (environment.config as MapApplicationConfig).apply {
                put("jwt.secret", "TODO-change-this-supersecret-or-use-SECRET-env")
            }
            application.module()
            test()
        }
    }

    fun TestApplicationRequest.setBodyJson(value: Any?) = setBody(Json.stringify(value))
}
