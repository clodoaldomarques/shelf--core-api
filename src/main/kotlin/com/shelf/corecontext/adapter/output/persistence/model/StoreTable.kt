package com.shelf.corecontext.adapter.output.persistence.model

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "store")
data class StoreTable(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "registrationDate", nullable = false)
    val registrationDate: OffsetDateTime

)