package com.example.schoolmangement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "roles")
@JsonIgnoreProperties("accounts")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    private var name: String? = null,
    @ManyToMany(mappedBy = "roles")
    var accounts: List<Account>? = null
) : GrantedAuthority{
    override fun toString(): String {
        return "Role(id=$id, name=$name)"
    }

    override fun getAuthority(): String? {
        return name
    }
    fun setName (name :String){
        this.name = name
    }
}
