package code.thexsvv.discordspring.config

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import java.nio.charset.StandardCharsets
import java.util.*

@Configuration
class DiscordApplicationConfig {

    @Value("\${bot.token}")
    private lateinit var token: String;

    @Bean
    fun bot(): DiscordApi {
        println(token);
        return DiscordApiBuilder()
            .setToken(token)
            .setAllIntents()
            .login()
            .join();
    }

    @Bean
    fun messageSource(): ReloadableResourceBundleMessageSource {
        val messageSource = ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

        return messageSource;
    }
}
