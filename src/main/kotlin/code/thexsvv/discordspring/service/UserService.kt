package code.thexsvv.discordspring.service

import code.thexsvv.discordspring.entity.User
import code.thexsvv.discordspring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService @Autowired constructor(private val repository: UserRepository) {

    @Transactional
    fun getOrCreateUser(id: Long, name: String): User {
        val user = repository.findById(id);
        return user.orElseGet {
            val newUser = User();
            newUser.id = id;
            newUser.name = name;
            newUser.playerRank = 1;
            newUser.level = 0;
            newUser.maxLevel = 10;

            repository.save(newUser);
            newUser
        }
    }

    @Transactional
    fun levelUp(id: Long, name: String) {
        val user = getOrCreateUser(id, name);
        val currentLevel = user.level ?: 0;
        val maxLevel = user.maxLevel ?: 10;

        if (currentLevel >= maxLevel) {
            val currentRank = user.playerRank ?: 1;
            user.playerRank = currentRank+1;
            user.level = 0;

            val baseMaxLevel = 10;
            val incrementFactor = 10;
            user.maxLevel = baseMaxLevel + (currentRank * incrementFactor);

            repository.save(user);
        } else {
            user.level = currentLevel+1;
            repository.save(user);
        }
    }
}
