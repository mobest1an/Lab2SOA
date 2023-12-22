package com.iver.agencyservice.config

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema


@Configuration
@EnableWs
class SoapWebServiceConfig : WsConfigurerAdapter() {
    @Bean(name = ["agency"])
    fun calculatorServiceDefinition(): DefaultWsdl11Definition {
        val wsdlDefinition = DefaultWsdl11Definition()
        wsdlDefinition.setPortTypeName("AgencyServicePort")
        wsdlDefinition.setLocationUri("/ws")
        wsdlDefinition.setTargetNamespace("http://com/iver")
        wsdlDefinition.setSchema(calculatorServiceSchema())
        return wsdlDefinition
    }

    @Bean
    fun messageDispatcherServlet(context: ApplicationContext?): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(context!!)
        servlet.isTransformWsdlLocations = true
        return ServletRegistrationBean(servlet, "ws/*")
    }

    @Bean
    fun calculatorServiceSchema(): XsdSchema {
        return SimpleXsdSchema(ClassPathResource("agency.xsd"))
    }
}