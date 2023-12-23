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
//    @Bean(name = ["agency"])
//    fun defaultWsdl11Definition(): DefaultWsdl11Definition {
//        val wsdlDefinition = DefaultWsdl11Definition()
//        wsdlDefinition.setPortTypeName("AgencyServicePort")
//        wsdlDefinition.setLocationUri("/ws")
//        wsdlDefinition.setTargetNamespace("http://com.iver")
//        wsdlDefinition.setSchema(calculatorServiceSchema())
//        return wsdlDefinition
//    }

//    @Bean
//    fun dispatcherServlet(applicationContext: ApplicationContext?): ServletRegistrationBean<*> {
//        val servlet = MessageDispatcherServlet()
//        servlet.setApplicationContext(applicationContext!!)
//        servlet.isTransformWsdlLocations = true
//        return ServletRegistrationBean(servlet, "/ws/*")
//    }

//    @Bean
//    fun exceptionResolver(): SoapFaultMappingExceptionResolver {
//        val exceptionResolver: SoapFaultMappingExceptionResolver = DetailSoapFaultDefinitionExceptionResolver()
//
//        val faultDefinition = SoapFaultDefinition()
//        faultDefinition.faultCode = SoapFaultDefinition.SERVER
//        exceptionResolver.setDefaultFault(faultDefinition)
//
//        val errorMappings = Properties()
//        errorMappings.setProperty(Exception::class.java.name, SoapFaultDefinition.SERVER.toString())
//        errorMappings.setProperty(ServiceFaultException::class.java.getName(), SoapFaultDefinition.SERVER.toString())
//        exceptionResolver.setExceptionMappings(errorMappings)
//        exceptionResolver.order = 1
//        return exceptionResolver
//    }

    @Bean(name = ["agency"])
    fun defaultWsdl11Definition(schema: XsdSchema?): DefaultWsdl11Definition {
        val wsdl11Definition = DefaultWsdl11Definition()
        wsdl11Definition.setPortTypeName("AgencyPort")
        wsdl11Definition.setLocationUri("/ws")
        wsdl11Definition.setTargetNamespace("http://com.iver")
        wsdl11Definition.setSchema(schema)
        return wsdl11Definition
    }

    @Bean
    fun messageDispatcherServlet(applicationContext: ApplicationContext?): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(applicationContext!!)
        servlet.isTransformWsdlLocations = true
        return ServletRegistrationBean(servlet, "/ws/*")
    }

    @Bean
    fun agencySchema(): XsdSchema {
        return SimpleXsdSchema(ClassPathResource("agency.xsd"))
    }
}