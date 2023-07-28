package code.thexsvv.discordspring.command.types

import code.thexsvv.discordspring.service.LocalizationService
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractCommand {

    @Autowired
    protected lateinit var localization: LocalizationService;
}
