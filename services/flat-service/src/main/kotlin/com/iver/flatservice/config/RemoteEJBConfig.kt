package com.iver.flatservice.config

import com.iver.flatejb.service.FlatService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.naming.Context
import javax.naming.InitialContext
import javax.naming.NamingException


@Configuration
class RemoteEJBConfig {

    @Bean
    @Throws(NamingException::class)
    fun context(): Context {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = "org.jboss.naming.remote.client.InitialContextFactory"
        jndiProps[Context.URL_PKG_PREFIXES] = "org.jboss.ejb.client.naming"
        jndiProps["jboss.naming.client.ejb.context"] = true
        jndiProps[Context.PROVIDER_URL] = "http-remoting://localhost:8080"
        return InitialContext(jndiProps)
    }

    @Bean
    @Throws(NamingException::class)
    fun flatServiceEjb(context: Context): FlatService {
        return context.lookup(this.getFullName(FlatService::class.java)) as FlatService
    }

    private fun getFullName(classType: Class<*>): String {
        val moduleName = "flat-ejb-0.0.1-SNAPSHOT/"
        val beanName = classType.simpleName
        val viewClassName = classType.name
        return "ejb:/$moduleName$beanName!$viewClassName"
    }
}
