package code.thexsvv.discordspring.command.types

import org.javacord.api.entity.message.Message
import org.javacord.api.entity.user.User

abstract class BasicCommand(
    private val name: String
) : AbstractCommand() {
    abstract fun onExecute(label: String, message: Message, user: User, args: Array<String>);

    fun getName(): String {
        return name;
    }
}
