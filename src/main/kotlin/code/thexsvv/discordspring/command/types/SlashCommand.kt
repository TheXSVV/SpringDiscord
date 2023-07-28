package code.thexsvv.discordspring.command.types

import org.javacord.api.DiscordApi
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.user.User
import org.javacord.api.interaction.SlashCommand
import org.javacord.api.interaction.SlashCommandInteraction

abstract class SlashCommand : AbstractCommand() {
    abstract fun create(api: DiscordApi): SlashCommand;
    abstract fun onExecute(slashCommand: SlashCommandInteraction, channel: TextChannel, user: User);
}
