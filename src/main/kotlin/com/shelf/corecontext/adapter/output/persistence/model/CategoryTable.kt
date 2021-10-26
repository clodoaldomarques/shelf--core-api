package com.shelf.corecontext.adapter.output.persistence.model

import javax.persistence.*

@Entity
@Table(name = "category")
data class CategoryTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int?,

    @Column(name = "name", nullable = false)
    val name : String
)