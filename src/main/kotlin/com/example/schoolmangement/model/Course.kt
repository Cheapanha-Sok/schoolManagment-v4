package com.example.schoolmangement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "courses")
@JsonIgnoreProperties("teachers")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var credit: Float? = null,
    var type: String? = null,
    @ManyToMany
    @JoinTable(
        name = "course_department",
        joinColumns = [JoinColumn(name = "course_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "department_id", referencedColumnName = "id")]
    )
    var departments: List<Department>? = null,
    @ManyToMany(mappedBy = "courses")
    var teachers: List<Teacher>? = null,
) {

    override fun toString(): String {
        return "Course(name=$name, credit=$credit, type=$type)"
    }
}
