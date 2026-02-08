package com.example.autogg.mixin;

import com.example.autogg.AutoGGMod;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
    private void onChatMessage(Text message, CallbackInfo ci) {
        String messageText = message.getString();
        
        // Check if this message indicates a match has ended
        if (messageText.contains("Match Complete")) {
            AutoGGMod.onChatMessage(messageText);
        }
    }
}
