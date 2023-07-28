package code.thexsvv.discordspring.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "app_user")
class User {

    @Id
    private val id: Long? = null;
    private val name: String? = null;
    private val level: Int? = null;
    private val rank: Int? = null;
}
