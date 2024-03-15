package com.example.schoolmangement.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "facultys")
@JsonIgnoreProperties("departments")
data class Faculty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    @Column(name = "dean_name")
    var deanName: String? = null,
    @Column(name = "office_number")
    var officeNumber: String? = null,
    @OneToMany(mappedBy = "faculty")
    var departments: List<Department>? = null
) {
    override fun toString(): String {
        return "Faculty(name=$name, deanName=$deanName, officeNumber=$officeNumber)"
    }
}
