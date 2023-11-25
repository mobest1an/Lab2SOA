package com.iver.flatejb.utils

import com.iver.flatejb.model.Coordinates
import com.iver.flatejb.model.Flat
import com.iver.flatejb.model.House
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityTransaction
import jakarta.persistence.SharedCacheMode
import jakarta.persistence.ValidationMode
import jakarta.persistence.spi.ClassTransformer
import jakarta.persistence.spi.PersistenceUnitInfo
import jakarta.persistence.spi.PersistenceUnitTransactionType
import org.hibernate.cfg.AvailableSettings.*
import org.hibernate.dialect.PostgreSQLDialect
import org.hibernate.jpa.HibernatePersistenceProvider
import java.io.IOException
import java.io.UncheckedIOException
import java.net.URL
import java.util.*
import java.util.function.Function
import javax.sql.DataSource


class HibernateFactory private constructor() {

    private val configMap = mutableMapOf<String, Any>()
    init {
//        configMap["jakarta.persistence.provider"] = "org.hibernate.jpa.HibernatePersistenceProvider"
//        configMap["jakarta.persistence.jtaDataSource"] = "java:/PostgresDS"
//        configMap["hibernate.hbm2ddl.auto"] = "update"
//        configMap["hibernate.show_sql"] = "true"
//
//        println("asdfasdf $configMap")

        configMap[JPA_JDBC_DRIVER] = "org.postgresql.Driver"
        configMap[JPA_JDBC_URL] = "jdbc:postgresql://localhost:54320/flat"
        configMap[JPA_JDBC_USER] = "flat"
        configMap[ JPA_JDBC_PASSWORD] = "flat"
        configMap[DIALECT] = PostgreSQLDialect::class.java
        configMap[HBM2DDL_AUTO] = "update"
        configMap[SHOW_SQL] = false
        configMap[QUERY_STARTUP_CHECKING] = false
        configMap[GENERATE_STATISTICS] = false
//        configMap[USE_REFLECTION_OPTIMIZER] = false
        configMap[USE_SECOND_LEVEL_CACHE] = false
        configMap[USE_QUERY_CACHE] = false
        configMap[USE_STRUCTURED_CACHE] = false
        configMap[STATEMENT_BATCH_SIZE] = 20
        configMap[LOADED_CLASSES] = listOf(Flat::class.java, Coordinates::class.java, House::class.java)


    }

    private val entityManagerFactory = HibernatePersistenceProvider().createContainerEntityManagerFactory(
        archiverPersistenceUnitInfo(), configMap)
//        ImmutableMap.<String, Object>builder()
//            .put(JPA_JDBC_DRIVER, org.postgresql.Driver)
//            .put(JPA_JDBC_URL, "jdbc:postgresql://flat:flat@localhost:54320/flat")
//            .put(DIALECT, PostgreSQLDialect.class)
//                .put(HBM2DDL_AUTO, "CREATE")
//                .put(SHOW_SQL, false)
//                .put(QUERY_STARTUP_CHECKING, false)
//                .put(GENERATE_STATISTICS, false)
//                .put(USE_REFLECTION_OPTIMIZER, false)
//                .put(USE_SECOND_LEVEL_CACHE, false)
//                .put(USE_QUERY_CACHE, false)
//                .put(USE_STRUCTURED_CACHE, false)
//                .put(STATEMENT_BATCH_SIZE, 20)
//                .build());

    private fun archiverPersistenceUnitInfo(): PersistenceUnitInfo {
        return object : PersistenceUnitInfo {
            override fun getPersistenceUnitName(): String {
                return "default"
            }

            override fun getPersistenceProviderClassName(): String {
                return "org.hibernate.jpa.HibernatePersistenceProvider"
            }

            override fun getTransactionType(): PersistenceUnitTransactionType {
                return PersistenceUnitTransactionType.RESOURCE_LOCAL
            }

            override fun getJtaDataSource(): DataSource? {
                return null
            }

            override fun getNonJtaDataSource(): DataSource? {
                return null
            }

            override fun getMappingFileNames(): List<String> {
                return Collections.emptyList()
            }

            override fun getJarFileUrls(): List<URL> {
                return try {
                    Collections.list(
                        this.javaClass
                            .getClassLoader()
                            .getResources("")
                    )
                } catch (e: IOException) {
                    throw UncheckedIOException(e)
                }
            }

            override fun getPersistenceUnitRootUrl(): URL? {
                return null
            }

            override fun getManagedClassNames(): List<String> {
                return Collections.emptyList()
            }

            override fun excludeUnlistedClasses(): Boolean {
                return false
            }

            override fun getSharedCacheMode(): SharedCacheMode? {
                return null
            }

            override fun getValidationMode(): ValidationMode? {
                return null
            }

            override fun getProperties(): Properties {
                return Properties()
            }

            override fun getPersistenceXMLSchemaVersion(): String? {
                return null
            }

            override fun getClassLoader(): ClassLoader? {
                return null
            }

            override fun addTransformer(transformer: ClassTransformer?) {}
            override fun getNewTempClassLoader(): ClassLoader? {
                return null
            }
        }
    }

//    @PersistenceContext(unitName = "default")
//    private lateinit var entityManager: EntityManager

    companion object {
        @Volatile
        private var instance: HibernateFactory = HibernateFactory()

        fun getInstance(): HibernateFactory {
            return instance
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
