# SpringDiscord - Discord bot made on Spring
#### Template for your discord bot based on Spring
<h4 align="center">
    Discord bot made using Spring, JPA, Hibernate, JavaCord and ♥
    <br>
    Supports listeners, commands, databases, and localization
</h4>

<div align="center">
    <a href="https://kotlinlang.org">
        <img alt="Kotlin" src="https://img.shields.io/badge/Language-Kotlin-7f52ff">
    </a>
    <a href="https://spring.io">
        <img alt="Eco System" src="https://img.shields.io/badge/Eco_System-Spring-green?logo=spring">
    </a>
    <a href="https://app.codacy.com/gh/TheXSVV/SpringDiscord/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade">
        <img alt="Codacy Review" src="https://app.codacy.com/project/badge/Grade/6e9e4f765d92440ba04345e75855f4c9">
    </a>
    <a href="https://github.com/adoptium/temurin17-binaries/releases/tag/jdk-17.0.8%2B7">
        <img alt="JDK" src="https://img.shields.io/badge/JDK-Temurin_17-orange">
    </a>
</div>

> **Note**
> 
> Click the <kbd>Use this template</kbd> button and clone it in IntelliJ IDEA or Another IDE

## Template structure
```
.
├── build/                  Output build directory
├── src                     Template sources
│   ├── main
│   │   ├── kotlin/         Kotlin sources
│   │   └── resources/      Resources - application.properties, messages
│   └── test
│       ├── kotlin/         Kotlin test sources
├── .gitignore              Git ignoring rules
├── build.gradle            Gradle configuration
├── gradle.properties       Gradle configuration properties
├── README.md               README file
└── settings.gradle         Gradle project settings
```

> **Note**
> 
> To use Java in your plugin, add `java` plugin to build.gradle and create the `/src/main/java` directory

## Requirements
Find the `application.properties` in `resources` folder and change following properties:
* **token** - With your discord bot token, you can generate it in [Discord Developer Portal](https://discord.com/developers/applications)
* **prefix** - With your bot prefix for [Basic Commands](https://github.com/TheXSVV/SpringDiscord/tree/master#basic-chat-command)

## Adding command
### Basic chat command
To create a basic chat command you need annotate your class with @Command and extend it with BasicCommand
Example:
```kotlin
@Command
class TestSlashCommand : code.thexsvv.discordspring.command.types.SlashCommand() {

    override fun create(api: DiscordApi): SlashCommand {
        return SlashCommand.with("ping", "Pongs you")
                .createGlobal(api)
                .join();
    }

    override fun onExecute(slashCommand: SlashCommandInteraction, channel: TextChannel, user: User) {
        slashCommand.createImmediateResponder()
                .setContent("Pong!")
                .respond();
    }
}
```

### Slash command
To create a slash command you need to annotate your class with @Command and extend it with SlashCommand
Example:
```kotlin
@Command
class TestBasicCommand : code.thexsvv.discordspring.command.types.BasicCommand("ping") {

    override fun onExecute(label: String, message: Message, user: User, args: Array<String>) {
        MessageBuilder()
            .setContent("Pong!")
            .send(message.channel);
        // or
        message.channel.sendMessage("Pong!");
    }
}
```

All command are registered automatically, because @Command is @Component and CommandRegistrar checks all beans in package


## Adding listener
To create a listener you need to annotate your class with @Listener and extend it with any listener in org.javacord.api.listener package
Example:
```kotlin
@Listener
class TestMessageListener : MessageCreateListener {

    override fun onMessageCreate(event: MessageCreateEvent) {
        if (!event.messageAuthor.isBotUser)
            event.message.reply("Hi");
    }
}
```

## Features
* [Basic chat commands](https://github.com/TheXSVV/SpringDiscord/tree/master#basic-chat-command)
* [Slash commands](https://github.com/TheXSVV/SpringDiscord/tree/master#slash-command)
* [Listeners](https://github.com/TheXSVV/SpringDiscord/tree/master#adding-listener)

[![Stargazers repo roster for @USERNAME/REPO_NAME](https://reporoster.com/stars/TheXSVV/SpringDiscord)](https://github.com/TheXSVV/SpringDiscord/stargazers)

## License
SpringDiscord is licensed under the [GPL-3.0 license](https://github.com/TheXSVV/SpringDiscord/blob/master/LICENSE)
