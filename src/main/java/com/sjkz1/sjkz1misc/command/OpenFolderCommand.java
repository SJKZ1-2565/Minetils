package com.sjkz1.sjkz1misc.command;

import java.io.File;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Util;

public class OpenFolderCommand {
    public OpenFolderCommand(CommandDispatcher<FabricClientCommandSource> dispatcher)
{
    var node = dispatcher.register(ClientCommandManager.literal("open_folder").executes(requirement -> OpenFolderCommand.openSFolder()));
    dispatcher.register(ClientCommandManager.literal("opf").redirect(node));
}

    private static int openSFolder()
    {
        Util.getOperatingSystem().open(new File(FabricLoader.getInstance().getGameDirectory(), "saves"));
        return 1;
    }
}
