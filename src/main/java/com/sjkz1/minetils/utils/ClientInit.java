package com.sjkz1.minetils.utils;

import com.sjkz1.minetils.Minetils;
import com.sjkz1.minetils.utils.enums.SkinPart;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class ClientInit {

    public static boolean dance = false;


    public static void tick(MinecraftClient client) {

        if (client.player != null) {
            SJKZ1Helper.runAsync(() ->
                    new DiscordMemberThread().start());
        }
        if (Minetils.danceKey.wasPressed()) {
            dance = !dance;
            client.getSoundManager().stopSounds(SoundEvents.MUSIC_DISC_PIGSTEP.getId(), SoundCategory.PLAYERS);
            if (dance) {
                client.player.playSound(SoundEvents.MUSIC_DISC_PIGSTEP, SoundCategory.PLAYERS, 1, 1);
            }
        }
        if (Minetils.showPost.wasPressed()) {
        	var i = client.player.getX();
        	var j = client.player.getY();
        	var k = client.player.getZ();
        	String pos = "X:" + i + " Y:" + j + " Z:" + k;
        	String NetherPos = "Nether position X:" + i / 8 + " Y:" + j + " Z:" + k / 8;
        	String OverWorldPose = "OverWorld position X:" + i * 8 + " Y:" + j + " Z:" + k * 8;


        	client.inGameHud.getChatHud().addMessage(Text.of(pos));
        	if (client.player.world.getDimension().isPiglinSafe()) {
        		client.inGameHud.getChatHud().addMessage(Text.of(OverWorldPose));
        	} else {
        		client.inGameHud.getChatHud().addMessage(Text.of(NetherPos));
        	}
        }
    }

    public static void login(ClientPlayNetworkHandler clientPlayNetworkHandler, PacketSender packetSender, MinecraftClient minecraftClient) {
        if(minecraftClient.world != null && !Minetils.CONFIG.getConfig().manualSkinEditor) {
        	SJKZ1Helper.runAsync(() -> ColorMatching.createGlowingSkinImageWithCustomUV(SkinPart.Part.HEAD.getMaxUvX(),SkinPart.Part.HEAD.getMaxUvY()));
        }
    }
}

