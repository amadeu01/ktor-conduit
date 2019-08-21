package dev.amadeu

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import kotlin.reflect.*
import java.util.*
import io.ktor.auth.jwt.*
import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import io.ktor.swagger.experimental.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val myjwt = MyJWT(secret = environment.config.property("jwt.secret").getString())

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost()
    }

    install(Authentication) {
        jwt("Token") {
            authSchemes("Bearer", "Token")
            verifier(myjwt.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("name").asString())
            }
        }
    }

    install(ContentNegotiation) {
        gson {
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val client = HttpClient(Apache) {
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

            exception<HttpException> {  cause ->
                call.respond(cause.code, cause.description)
            }
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        ConduitAPIServer(myjwt).apply {
            registerUserAndAuthentication()
            registerProfile()
            registerArticles()
            registerComments()
            registerFavorites()
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

open class MyJWT(val secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier = JWT.require(algorithm).build()
    fun sign(name: String): String = JWT.create().withClaim("name", name).sign(algorithm)
}

