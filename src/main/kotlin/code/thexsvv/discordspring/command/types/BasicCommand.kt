package code.thexsvv.discordspring.command.types

import org.javacord.api.entity.message.Message

abstract class BasicCommand(
    private val name: String
) {
    abstract fun onExecute(label: String, message: Message, args: Array<String>);

    fun getName(): String {
        return name;
    }
}
