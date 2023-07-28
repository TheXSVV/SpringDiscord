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
        if (user.isEmpty)
            repository.registerUser(id, name);

        return repository.findById(id).get();
    }
}
