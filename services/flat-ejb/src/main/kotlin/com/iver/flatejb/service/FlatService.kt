package com.iver.flatejb.service

import com.iver.flatejb.utils.checkAscOrDesc
import com.iver.flatejb.model.*
import com.iver.flatejb.utils.FlatSpecificationBuilder
import com.iver.flatejb.utils.HibernateFactory
import com.iver.flatejb.utils.SortingParameters
import com.iver.flatejb.utils.SortingType
import javax.ejb.Remote
import javax.ejb.Stateless

@Remote
interface FlatService {
    fun getFlatById(flatId: Long): Flat
    fun createFlat(flat: Flat): Flat
    fun updateFlat(flat: Flat): Flat
    fun deleteFlat(flatId: FlatId)
    fun deleteByTransport(transportCode: String)
    fun calculateSumOfRoomNumbers(): Long
    fun getFlatsNameStartsWith(subName: String): FlatsRepresentation
    fun getAllFlats(sort: String?): FlatsRepresentation
    fun getAllFlatsPageable(page: Int, size: Int, sort: String?): FlatsRepresentation
    fun getAllFlatsBySearchCriteria(
        filters: Array<String>, sort: SortingParameters?,
    ): List<Flat>
    fun getAllFlatsBySearchCriteriaPageable(
        filters: Array<String>, sort: SortingParameters?,
        page: Int, size: Int,
    ): FlatsRepresentation
}

@Stateless(name = "FlatService")
open class FlatServiceImpl: FlatService {

    private val hibernateFactory = HibernateFactory.getInstance()

    override fun getFlatById(flatId: Long): Flat {
        return hibernateFactory.runInTransaction {
            it.createQuery("SELECT * From flat WHERE id = $flatId", Flat::class.java).singleResult
        }
    }

    override fun createFlat(flat: Flat): Flat {
        hibernateFactory.runInTransaction {
            it.persist(flat)
        }
        return flat
    }

    override fun updateFlat(flat: Flat): Flat {
        hibernateFactory.runInTransaction {
            it.persist(flat)
        }
        return flat
    }

    override fun deleteFlat(flatId: FlatId) {
        hibernateFactory.runInTransaction {
            it.createQuery("DELETE * FROM flat WHERE id = $flatId", Flat::class.java)
        }
    }

    override fun deleteByTransport(transportCode: String) {
        val flat = hibernateFactory.runInTransaction {
            it.createQuery("SELECT * FROM flat WHERE transport = $transportCode", Flat::class.java).resultList[0]
        }
        deleteFlat(flat.id)
    }

    override fun calculateSumOfRoomNumbers(): Long {
        val flats = getAllFlats(null).flats
        var sum = 0L
        for (flat in flats) {
            sum += flat.numberOfRooms
        }

        return sum
    }

    override fun getFlatsNameStartsWith(subName: String): FlatsRepresentation {
        val result = hibernateFactory.runInTransaction {
            it.createQuery("SELECT * From flat WHERE name LIKE $subName%", Flat::class.java).resultList
        }

        return FlatsRepresentation(
            flats = result.map {
                FlatView(it)
            },
            elements = result.size.toLong(),
            pages = 0,
        )
    }

    override fun getAllFlats(sort: String?): FlatsRepresentation {
        val result = hibernateFactory.runInTransaction {
            it.createQuery("SELECT * From flat ORDER BY ${checkAscOrDesc(sort ?: "id")}", Flat::class.java)
                .resultList
        }

        return FlatsRepresentation(
            flats = result.map {
                FlatView(it)
            },
            elements = result.size.toLong(),
            pages = 0,
        )
    }

    override fun getAllFlatsPageable(page: Int, size: Int, sort: String?): FlatsRepresentation {
        val result = hibernateFactory.runInTransaction {
            it.createQuery("SELECT * From flat ORDER BY ${checkAscOrDesc(sort ?: "id")}", Flat::class.java)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .resultList
        }.map {
            FlatView(it)
        }

        return FlatsRepresentation(
            flats = result,
            elements = result.size.toLong(),
            pages = page,
        )
    }

    override fun getAllFlatsBySearchCriteria(
        filters: Array<String>, sort: SortingParameters?,
    ): List<Flat> {
        val flatSpecificationBuilder = FlatSpecificationBuilder();
        flatSpecificationBuilder.parseCriteria(filters)
        val query = flatSpecificationBuilder.build()

        val criteriaBuilder = hibernateFactory.getEntityManager().criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Flat::class.java)
        val root = criteriaQuery.from(Flat::class.java)

       if (sort != null) {
           if (sort.sortingType == SortingType.DESC) {
               if (sort.nested) {
                   query!!.orderBy(criteriaBuilder.desc(root.get<Any>(sort.fieldName).get<Any>(sort.nestedFieldName)))
               } else {
                   query!!.orderBy(criteriaBuilder.desc(root.get<Any>(sort.fieldName)))
               }
           } else {
               if (sort.nested) {
                   query!!.orderBy(criteriaBuilder.asc(root.get<Any>(sort.fieldName).get<Any>(sort.nestedFieldName)))
               } else {
                   query!!.orderBy(criteriaBuilder.asc(root.get<Any>(sort.fieldName)))
               }
           }
       }

        return hibernateFactory.runInTransaction {
            it.createQuery(query)
        }.resultList
    }

    override fun getAllFlatsBySearchCriteriaPageable(
        filters: Array<String>, sort: SortingParameters?,
        page: Int, size: Int,
    ): FlatsRepresentation {
        val flatSpecificationBuilder = FlatSpecificationBuilder();
        flatSpecificationBuilder.parseCriteria(filters)
        val query = flatSpecificationBuilder.build()

        val criteriaBuilder = hibernateFactory.getEntityManager().criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Flat::class.java)
        val root = criteriaQuery.from(Flat::class.java)

        if (sort != null) {
            if (sort.sortingType == SortingType.DESC) {
                if (sort.nested) {
                    query!!.orderBy(criteriaBuilder.desc(root.get<Any>(sort.fieldName).get<Any>(sort.nestedFieldName)))
                } else {
                    query!!.orderBy(criteriaBuilder.desc(root.get<Any>(sort.fieldName)))
                }
            } else {
                if (sort.nested) {
                    query!!.orderBy(criteriaBuilder.asc(root.get<Any>(sort.fieldName).get<Any>(sort.nestedFieldName)))
                } else {
                    query!!.orderBy(criteriaBuilder.asc(root.get<Any>(sort.fieldName)))
                }
            }
        }

        val result = hibernateFactory.runInTransaction {
            it.createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .resultList
        }.map {
            FlatView(it)
        }

        return FlatsRepresentation(
            flats = result,
            elements = result.size.toLong(),
            pages = page,
        )
    }
}
