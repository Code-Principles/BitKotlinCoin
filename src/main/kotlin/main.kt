import Utils.addNewBlock
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.body
import kotlinx.html.p

private val blockChain = mutableListOf<Block>().also {
    it.add(
            Block(
                    0,
                    Utils.sha256("0"),
                    System.currentTimeMillis() / 1000,
                    null
            )
    )
}

fun main(args: Array<String>) {
    embeddedServer(
            Netty,
            watchPaths = listOf("./"),
            port = 3000,
            module = Application::myModule
    ).start(true)
}

fun Application.myModule() {
    routing {
        get("/") {
            call.respondHtml {
                body {
                    blockChain.forEach {
                        p { text(it.toString()) }
                    }
                }
            }
        }
        get("/add") {
            blockChain.addNewBlock()
            call.respondHtml {
                body {
                    p { text("Added block to block chain! :D") }
                }
            }
        }
    }
}