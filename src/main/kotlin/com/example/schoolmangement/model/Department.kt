package com.example.schoolmangement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "departments")
@JsonIgnoreProperties( "courses" ,"students")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    @Column(name = "head_name")
    var headName: String? = null,
    @Column(name = "office_number")
    var officeNumber: String? = null,
    // Relation faculty with department
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    var faculty: Faculty? = null,
    // Relation student with department
    @ManyToMany(mappedBy = "departments")
    var students: List<Student>? = null,
    // Relation course with department
    @ManyToMany(mappedBy = "departments")
    var courses: List<Course>? = null

) {

    override fun toString(): String {
        return "Department(id=$id, name=$name, headName=$headName, officeNumber=$officeNumber)"
    }
}
