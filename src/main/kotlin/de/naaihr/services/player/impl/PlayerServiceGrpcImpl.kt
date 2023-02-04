package de.naaihr.services.player.impl

import de.naaihr.services.player.proto.*
import org.checkerframework.common.returnsreceiver.qual.This
import java.util.logging.Logger

class PlayerServiceGrpcImpl : PlayerServiceGrpcKt.PlayerServiceCoroutineImplBase() {

    override suspend fun healthCheck(request: HealthCheckRequest): HealthCheckResponse {
        LOGGER.info("Receiving new healthCheck request ($request)")
        return HealthCheckResponse.newBuilder().setAnswer(true).build()
    }

    override suspend fun getPlayer(uniqueId: UniqueId): CorePlayer {
        LOGGER.info("Receiving new healthCheck request ($uniqueId)")
        //normally return player from cache/ db
        return CorePlayer.getDefaultInstance()
    }

    override suspend fun savePlayer(corePlayer: CorePlayer): CorePlayer {
        LOGGER.info("Receiving new healthCheck request ($corePlayer)")
        return corePlayer
    }

    override suspend fun getGlobalPlayerCount(request: GlobalPlayerCountRequest): GlobalPlayerCountResponse {
        LOGGER.info("Receiving new healthCheck request ($request)")
        return globalPlayerCountResponse { count = 123 } // Count objects in cache
    }

    companion object {
        val LOGGER: Logger = Logger.getLogger(This::class.java.name)
    }

}