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

            repository.save(newUser);
            newUser
        }
    };
}
