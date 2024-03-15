package com.example.schoolmangement.model

import jakarta.persistence.*

@Entity
@Table(name = "teachers")
data class Teacher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var gender: String? = null,
    var address: String? = null,
    @Column(name = "phone_number")
    var phoneNumber: String? = null,
    @ManyToMany
    @JoinTable(
        name = "teacher_course",
        joinColumns = [JoinColumn(name = "teacher_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "course_id", referencedColumnName = "id")]
    )
    var courses: List<Course>? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    var account:Account ? = null,

) {
    override fun toString(): String {
        return "Teacher(id=$id, name=$name, gender=$gender, address=$address, phoneNumber=$phoneNumber)"
    }
}
