package code.thexsvv.discordspring.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class LocalizationService {

    @Autowired
    private lateinit var messageSource: MessageSource;

    fun localize(key: String): String {
        return localize(key);
    }

    @SafeVarargs
    fun localize(key: String, vararg args: String): String {
        var result = messageSource.getMessage(key, args, Locale.forLanguageTag("ru"));
        for (i in args.indices)
            result = result.replace("{$i}", args[i]);

        return result;
    }
}
