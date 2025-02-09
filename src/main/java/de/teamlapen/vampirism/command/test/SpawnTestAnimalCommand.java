package de.teamlapen.vampirism.command.test;

import com.mojang.brigadier.builder.ArgumentBuilder;
import de.teamlapen.lib.lib.util.BasicCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SpawnTestAnimalCommand extends BasicCommand {

    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("spawnTestAnimal")
                .requires(context -> context.hasPermission(PERMISSION_LEVEL_ADMIN))
                .executes(context -> {
                    return spawnTestAnimal(context.getSource().getPlayerOrException());
                });
    }

    private static int spawnTestAnimal(ServerPlayerEntity asPlayer) {
        CowEntity cow = EntityType.COW.create(asPlayer.getCommandSenderWorld());
        cow.setHealth(cow.getMaxHealth() / 4.2f);
        cow.copyPosition(asPlayer);
        asPlayer.level.addFreshEntity(cow);
        return 0;
    }
}
