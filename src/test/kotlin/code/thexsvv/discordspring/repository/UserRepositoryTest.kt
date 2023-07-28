package code.thexsvv.discordspring.repository

import code.thexsvv.discordspring.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun testFindUserByName_UserExists() {
        val userName = "Test User";
        val user = User().apply {
            this.id = 1L;
            this.name = userName;
            this.level = 0;
            this.rank = 1;
        }
        userRepository.save(user);

        assertEquals(user, userRepository.findUserByName(userName).get());
    }
}
