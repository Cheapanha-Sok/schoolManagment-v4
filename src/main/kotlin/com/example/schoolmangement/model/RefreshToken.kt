package com.example.schoolmangement.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "refresh_tokens")
data class RefreshToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long ? =null,
    @Column(unique=true)
    var token :String ? = null,
    @Column(name = "expiry_date")
    var expiryDate :Instant ? =null,
    @OneToOne
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    var account :Account ? =null
)
