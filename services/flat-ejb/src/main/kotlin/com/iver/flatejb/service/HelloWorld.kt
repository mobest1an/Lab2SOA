package com.iver.flatejb.service

import jakarta.ejb.Remote
import jakarta.ejb.Stateless

@Remote
interface HelloStatelessWorld {
    fun getHelloWorld(): String
}

@Stateless(name = "HelloStatelessWorld")
open class HelloStatelessWorldBean : HelloStatelessWorld {
    override fun getHelloWorld(): String {
        return "Hello Stateless World!"
    }
}
