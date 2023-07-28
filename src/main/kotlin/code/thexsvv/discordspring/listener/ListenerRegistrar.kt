package code.thexsvv.discordspring.listener

import org.apache.logging.log4j.LogManager
import org.javacord.api.DiscordApi
import org.javacord.api.listener.GloballyAttachableListener
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class ListenerRegistrar(
    ctx: ApplicationContext,
    api: DiscordApi
) {

    private val LOGGER = LogManager.getLogger(javaClass);

    init {
        val listeners = ctx.getBeansWithAnnotation(Listener::class.java);
        for (listener in listeners.values) {
            if (listener is GloballyAttachableListener) {
                api.addListener(listener);
                LOGGER.info("Registered listener: "+listener.javaClass.simpleName);
            }
        }
    }
}
