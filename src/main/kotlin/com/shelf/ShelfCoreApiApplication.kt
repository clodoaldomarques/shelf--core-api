package com.shelf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShelfCoreApiApplication

fun main(args: Array<String>) {
	runApplication<ShelfCoreApiApplication>(*args)
}
