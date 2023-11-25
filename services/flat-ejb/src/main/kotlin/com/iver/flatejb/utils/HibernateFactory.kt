package com.iver.flatejb.utils

import javax.persistence.EntityManager
import javax.persistence.EntityTransaction
import javax.persistence.Persistence
import java.util.function.Function

class HibernateFactory private constructor() {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("flat")

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

    fun shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close()
            instance = null
        }
    }

    fun createEntityManager(): EntityManager {
        return entityManagerFactory.createEntityManager()
    }

    fun <T> runInTransaction(function: Function<EntityManager, T>): T {
        val entityManager: EntityManager = entityManagerFactory.createEntityManager()
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
