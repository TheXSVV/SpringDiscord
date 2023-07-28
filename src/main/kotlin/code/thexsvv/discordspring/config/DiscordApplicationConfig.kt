package code.thexsvv.discordspring.config

import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.entity.intent.Intent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscordApplicationConfig {

    @Value("\${bot.token}")
    private lateinit var token: String;

    @Bean
    fun bot(): DiscordApi {
        return DiscordApiBuilder()
            .setToken(token)
            .addIntents(*Intent.values())
            .login()
            .join();
    }
}
