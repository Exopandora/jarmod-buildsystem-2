package com.example.mod;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;

public class FillLimitCommand {

    public static int fillLimit = 32768;
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("fill")
                .then(literal("limit")
                        .executes(context -> getFillLimit(context.getSource()))
                        .then(argument("newLimit", IntegerArgumentType.integer(0))
                                .executes(context -> setFillLimit(context.getSource(), IntegerArgumentType.getInteger(context, "newLimit"))))));
    }
    
    private static int getFillLimit(CommandSourceStack source) {
        source.sendSuccess(new TextComponent("Fill limit: " + fillLimit), false);
        return fillLimit;
    }
    
    private static int setFillLimit(CommandSourceStack source, int newLimit) {
        fillLimit = newLimit;
        source.sendSuccess(new TextComponent("Fill limit updated to " + newLimit), true);
        return 0;
    }
    
}
