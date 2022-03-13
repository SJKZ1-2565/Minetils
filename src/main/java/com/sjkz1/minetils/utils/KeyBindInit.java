package com.sjkz1.minetils.utils;

import org.lwjgl.glfw.GLFW;

import com.sjkz1.minetils.Minetils;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyBindInit {
    public static void init()
    {
        Minetils.danceKey = KeyBindingHelper.registerKeyBinding(new KeyBinding ("key.sjkz1misc.start_dance", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, Minetils.MOD_ID));
        Minetils.showPost = KeyBindingHelper.registerKeyBinding(new KeyBinding ("key.sjkz1misc.showPost", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, Minetils.MOD_ID));
    }
}
