package de.naaihr.services.player

import de.naaihr.services.player.impl.PlayerServiceGrpcImpl
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import org.checkerframework.common.returnsreceiver.qual.This
import java.util.logging.Logger

class PlayerService constructor(
    private val port: Int
) {
    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(PlayerServiceGrpcImpl())
        .addService(ProtoReflectionService.newInstance())
        .build()

    fun start() {
        server.start()
        LOGGER.info("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                LOGGER.warning("JVM stopped. Shutting down player-service now")
                this@PlayerService.stop()
            }
        )
    }

    private fun stop() {
        server.shutdown()
        println("player-service shut down")
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    companion object {
        val LOGGER: Logger = Logger.getLogger(This::class.java.name)
    }
}


fun main() {
    val port = 50052
    val server = PlayerService(port)
    server.start()
    server.blockUntilShutdown()
}
