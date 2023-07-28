package code.thexsvv.discordspring.repository

import code.thexsvv.discordspring.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("SELECT u FROM User u")
    fun getUsers(): List<User>;

    @Query("SELECT u FROM User u WHERE u.id = :id")
    override fun findById(id: Long): Optional<User>;

    @Query("SELECT u FROM User u WHERE u.name = :name")
    fun findUserByName(name: String): Optional<User>;

    @Query("SELECT u.level FROM User u WHERE u = :user")
    fun getUserLevel(@Param("user") user: User): Int;

    @Query("SELECT u.rank FROM User u WHERE u = :user")
    fun getUserRank(@Param("user") user: User): Int;

    @Modifying
    @Query("INSERT INTO User (id, name, level, rank) VALUES (:id, :name, 0, 1)")
    fun registerUser(@Param("id") id: Long, @Param("name") name: String);
}
