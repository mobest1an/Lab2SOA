package com.iver.flatejb.utils

import jakarta.annotation.Resource
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityTransaction
import jakarta.persistence.Persistence
import java.util.function.Function
import javax.sql.DataSource

class HibernateFactory private constructor() {

//    @Resource(name = "java:/PostgresDS")
//    private lateinit var dataSource: DataSource

    private val configMap = mutableMapOf<String, String>()

    init {
        configMap["javax.persistence.provider"] = "org.hibernate.jpa.HibernatePersistenceProvider"
        configMap["javax.persistence.jtaDataSource"] = "java:/PostgresDS"
        configMap["hibernate.hbm2ddl.auto"] = "update"
        configMap["hibernate.show_sql"] = "true"
    }

    private val entityManagerFactory = Persistence.createEntityManagerFactory("default", configMap)

//    @PersistenceContext(unitName = "default")
//    private lateinit var entityManager: EntityManager

    companion object {
        @Volatile
        private var instance: HibernateFactory? = null

        fun getInstance(): HibernateFactory {
            if (instance == null) {
                synchronized(HibernateFactory::class.java) {
                    if (instance == null) {
                        instance = HibernateFactory()
                    }
                }
            }
            return instance!!
        }
    }

//    fun shutdown() {
//        if (entityManagerFactory != null) {
//            entityManagerFactory.close()
//            instance = null
//        }
//    }

    fun getEntityManager(): EntityManager {
        return entityManagerFactory.createEntityManager()
    }

    fun <T> runInTransaction(function: Function<EntityManager, T>): T {
        val entityManager = entityManagerFactory.createEntityManager()
        val transaction: EntityTransaction = entityManager.transaction
        transaction.begin()
        var success = false
        return try {
            val returnValue: T = function.apply(entityManager)
            success = true
            returnValue
        } finally {
            if (success) {
                transaction.commit()
            } else {
                transaction.rollback()
            }
        }
    }
}
