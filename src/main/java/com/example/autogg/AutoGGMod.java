package com.example.autogg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoGGMod implements ModInitializer {
    public static final String MOD_ID = "auto-gg-mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static final String MATCH_COMPLETE_TEXT = "Match Complete";
    private static final String GG_MESSAGE = "gg";
    private static boolean hasSentGG = false;
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Auto GG Mod!");
        LOGGER.info("Mod will automatically send 'gg' when 'Match Complete' is detected in chat");
        
        // Register disconnect event to reset state
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            hasSentGG = false;
            LOGGER.info("Disconnected from server - reset GG state");
        });
    }
    
    public static void onChatMessage(String message) {
        if (message.contains(MATCH_COMPLETE_TEXT) && !hasSentGG) {
            LOGGER.info("Match Complete detected! Sending gg...");
            sendGGMessage();
            hasSentGG = true;
            
            // Reset after 5 seconds to allow for new matches
            scheduler.schedule(() -> {
                hasSentGG = false;
                LOGGER.info("Reset GG flag - ready for next match");
            }, 5, TimeUnit.SECONDS);
        }
    }
    
    private static void sendGGMessage() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && client.getNetworkHandler() != null) {
            // Send the gg message using the player's chat method
            client.player.networkHandler.sendChatMessage(GG_MESSAGE);
            LOGGER.info("Sent gg message!");
        }
    }
}
