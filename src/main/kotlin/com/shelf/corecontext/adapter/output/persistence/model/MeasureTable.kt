package com.shelf.corecontext.adapter.output.persistence.model

import javax.persistence.*

@Entity
@Table(name = "measure")
data class MeasureTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int?,

    @Column(name = "name", nullable = false)
    val name : String,

    @Column(name = "initials", nullable = false)
    val initials : String

)