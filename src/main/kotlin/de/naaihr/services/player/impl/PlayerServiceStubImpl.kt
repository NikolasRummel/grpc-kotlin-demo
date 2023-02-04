package de.naaihr.services.player.impl

import de.naaihr.services.player.proto.*
import io.grpc.ManagedChannel
import org.checkerframework.common.returnsreceiver.qual.This
import java.util.logging.Logger

// Interface for the stub, which will performed in the client
interface PlayerService {
    suspend fun healthCheck(request: HealthCheckRequest): HealthCheckResponse
    suspend fun getPlayer(uniqueId: UniqueId): CorePlayer
    suspend fun savePlayer(corePlayer: CorePlayer): CorePlayer
    suspend fun getGlobalPlayerCount(request: GlobalPlayerCountRequest): GlobalPlayerCountResponse
}

class PlayerServiceStubImpl constructor(
    channel: ManagedChannel
) : PlayerService {

    private val stub: PlayerServiceGrpcKt.PlayerServiceCoroutineStub =
        PlayerServiceGrpcKt.PlayerServiceCoroutineStub(channel)

    override suspend fun healthCheck(request: HealthCheckRequest): HealthCheckResponse {
        LOGGER.info("Performing new healthCheck request ($request)")
        return stub.healthCheck(request)
    }

    override suspend fun getPlayer(uniqueId: UniqueId): CorePlayer {
        LOGGER.info("Performing new healthCheck request ($uniqueId)")
        return stub.getPlayer(uniqueId)
    }

    override suspend fun savePlayer(corePlayer: CorePlayer): CorePlayer {
        LOGGER.info("Performing new healthCheck request ($corePlayer)")
        return stub.savePlayer(corePlayer)
    }

    override suspend fun getGlobalPlayerCount(request: GlobalPlayerCountRequest): GlobalPlayerCountResponse {
        LOGGER.info("Performing new healthCheck request ($request)")
        return stub.getGlobalPlayerCount(request)
    }

    companion object {
        val LOGGER: Logger = Logger.getLogger(This::class.java.name)
    }
}