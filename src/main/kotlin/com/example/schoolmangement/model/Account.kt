package com.example.schoolmangement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "accounts")
@JsonIgnoreProperties("roles" , "teacher" , "student")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "username")
    private var name: String? = null,
    private var password: String? = null,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "account_role",
        joinColumns = [JoinColumn(name = "account_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: MutableList<Role>? = null,
    @OneToOne(mappedBy = "account")
    var teacher: Teacher? = null,
    @OneToOne(mappedBy = "account")
    var student :Student? = null
) : UserDetails {

    override fun toString(): String {
        return "Account(id=$id, username=$username, password=$password)"
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return roles
    }
    fun setPassword(password: String){
        this.password = password
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return name
    }
    fun setName(name :String){
        this.name = name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
