package code.thexsvv.discordspring.service

import code.thexsvv.discordspring.entity.User
import code.thexsvv.discordspring.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var repository: UserRepository;

    @InjectMocks
    private lateinit var userService: UserService;

    @Test
    fun testGetOrCreateUser_UserDoesNotExist() {
        val id = 1L;
        val name = "Test User";
        val emptyUser: Optional<User> = Optional.empty();

        doReturn(emptyUser).`when`(repository).findById(id);

        assertEquals(name, userService.getOrCreateUser(id, name).name);
    }
}
