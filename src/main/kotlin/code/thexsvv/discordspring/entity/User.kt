package code.thexsvv.discordspring.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Getter

@Entity
@Table(name = "app_user")
@Getter
class User {

    @Id
    var id: Long? = null;
    var name: String? = null;
    var level: Int? = null;
    var maxLevel: Int? = null;
    var rank: Int? = null;

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true;
        if (other !is User)
            return false;

        return id == other.id;
    }

    override fun hashCode(): Int {
        return id.hashCode() + name.hashCode();
    }
}
