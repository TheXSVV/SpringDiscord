package code.thexsvv.discordspring.listener.impl

import code.thexsvv.discordspring.listener.Listener
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener

@Listener
class TestMessageListener : MessageCreateListener {

    override fun onMessageCreate(event: MessageCreateEvent) {
        //event.message.reply("Hi");
    }
}
