package de.naaihr.services.player

import de.naaihr.services.player.impl.PlayerServiceStubImpl
import de.naaihr.services.player.proto.globalPlayerCountRequest
import de.naaihr.services.player.proto.healthCheckRequest
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.io.Closeable
import java.util.concurrent.TimeUnit

class PlayerServiceClientTest(private val channel: ManagedChannel) : Closeable {

    val stub: PlayerServiceStubImpl = PlayerServiceStubImpl(channel)

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}

suspend fun main(args: Array<String>) {
    val port = 50052
    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()
    val clientTest = PlayerServiceClientTest(channel)

    val response = clientTest.stub.getGlobalPlayerCount(globalPlayerCountRequest { })
    println(response)
}

