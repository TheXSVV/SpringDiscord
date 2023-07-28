package code.thexsvv.discordspring.command.impl

import code.thexsvv.discordspring.command.Command
import org.javacord.api.DiscordApi
import org.javacord.api.entity.channel.TextChannel
import org.javacord.api.entity.user.User
import org.javacord.api.interaction.SlashCommand
import org.javacord.api.interaction.SlashCommandInteraction

@Command
class TestSlashCommand : code.thexsvv.discordspring.command.types.SlashCommand() {

    override fun create(api: DiscordApi): SlashCommand {
        return SlashCommand.with(
            "ping", "Pongs you)"
        )
        .createGlobal(api)
        .join();
    }

    override fun onExecute(slashCommand: SlashCommandInteraction, channel: TextChannel, user: User) {
        slashCommand.createImmediateResponder().setContent("Pong!").respond();
    }
}
