package code.thexsvv.discordspring.command

import code.thexsvv.discordspring.command.types.BasicCommand
import code.thexsvv.discordspring.command.types.SlashCommand
import org.apache.logging.log4j.LogManager
import org.javacord.api.DiscordApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommandRegistrar(
    ctx: ApplicationContext,
    api: DiscordApi
) {

    private val LOGGER = LogManager.getLogger(javaClass);

    @Value("\${bot.prefix}")
    private lateinit var prefix: String;

    init {
        val commands = ctx.getBeansWithAnnotation(Command::class.java);
        for (command in commands.values) {
            if (command is SlashCommand) {
                val slashCommand = command.create(api);
                api.addInteractionCreateListener { event ->
                    if (event.slashCommandInteraction.isPresent && event.interaction.channel.isPresent && event.slashCommandInteraction.get().fullCommandName.equals(slashCommand.fullCommandNames[0]))
                        command.onExecute(event.slashCommandInteraction.get(), event.slashCommandInteraction.get().channel.get(), event.interaction.user);
                };
                LOGGER.info("Registered slash command: "+command.javaClass.simpleName);
            } else if (command is BasicCommand) {
                api.addMessageCreateListener { event ->
                    if (event.messageContent.startsWith(prefix+command.getName())) {
                        val args = event.messageContent.split(" ");
                        val argsWithoutLabel = args.drop(1).toTypedArray();

                        command.onExecute(args[0], event.message, event.message.userAuthor.get(), argsWithoutLabel);
                    }
                }
                LOGGER.info("Registered basic command via listener: "+command.javaClass.simpleName);
            }
        }
    }
}
