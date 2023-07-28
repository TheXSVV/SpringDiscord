package code.thexsvv.discordspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties
class DiscordApplication

object Launcher {

    @JvmStatic
    fun main(args: Array<String>) {
        Thread.currentThread().name = "app";
        SpringApplicationBuilder(DiscordApplication::class.java)
            .profiles("development")
            .headless(true)
            .application()
            .run();
    }
}
