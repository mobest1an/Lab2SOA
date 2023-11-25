package com.iver.flatejb.utils

import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import java.util.function.Function
import javax.persistence.PersistenceContext

class HibernateFactory private constructor() {

//    private val entityManagerFactory = Persistence.createEntityManagerFactory("flat")

    @PersistenceContext(unitName = "db_unit")
    private lateinit var entityManager: EntityManager

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
        return entityManager
    }

    fun <T> runInTransaction(function: Function<EntityManager, T>): T {
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
