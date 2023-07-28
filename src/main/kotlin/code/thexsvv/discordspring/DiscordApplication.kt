package code.thexsvv.discordspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class DiscordApplication {

}

fun main() {
    Thread.currentThread().name = "app";
    runApplication<DiscordApplication>();
}
