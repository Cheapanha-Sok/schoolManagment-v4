package com.example.schoolmangement.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate
import java.time.Period
import kotlin.jvm.Transient

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @JsonFormat(pattern = "yyyy-MM-dd")
    var dob: LocalDate? = null,
    var name: String? = null,
    var gender: String? = null,
    @Column(name = "phone_number")
    var phoneNumber: String? = null,
    var address: String? = null,
    var generation: Int? = null,
    var year: Int? = null,
    var degree: String? = null,

    @ManyToMany
    @JoinTable(
        name = "students_enrolled",
        joinColumns = [JoinColumn(name = "student_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "department_id", referencedColumnName = "id")]
    )
    var departments: List<Department>? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    var account :Account ? = null
) {
    override fun toString(): String {
        return "Student(id=$id, dob=$dob, name=$name, gender=$gender, phoneNumber=$phoneNumber, address=$address, generation=$generation, year=$year, degree=$degree)"
    }
}

