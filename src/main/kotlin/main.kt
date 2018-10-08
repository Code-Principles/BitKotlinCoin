import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.body
import kotlinx.html.p



fun main(args: Array<String>) {
    embeddedServer(
            Netty,
            watchPaths = listOf("./"),
            port = 3001,
            module = Application::myModule
    ).start(true)
}

fun Application.myModule() {
    routing {
        get("/") {
            call.respondHtml {
                body {
                    BlockChain.get().forEach {
                        p { text(it.toString()) }
                    }
                }
            }
        }
        get("/add") {
            BlockChain.addNewBlock()
            call.respondHtml {
                body {
                    p { text("Added block to block chain! :D") }
                }
            }
        }
        get("/isValid") {
            call.respondHtml {
                body {
                    p {
                        text("The chain is " +
                                if (BlockChain.isValid(BlockChain.get()))
                                    "valid"
                                else
                                    "invalid")
                    }
                }
            }
        }
    }
}