package com.example.schoolmangement.base.repository

import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.*

@NoRepositoryBean
interface BaseRepository <T , ID> : Repository<T , ID> , JpaSpecificationExecutor<T> {
    /**
     * Saves all given entities.
     *
     * @param entities must not be null nor must it contain null.
     * @return the saved entities; will never be null. The returned Iterable will have the same size
     * as the Iterable passed as an argument.
     * @throws IllegalArgumentException in case the given [entities][Iterable] or one of its entities is
     * null.
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has a version
     * attribute with a different value from that found in the persistence store. Also thrown if at least one
     * entity is assumed to be present but does not exist in the database.
     */
    fun <S : T?> saveAll(entities: Iterable<S>?): Iterable<S>?

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be null.
     * @return the entity with the given id or Optional#empty() if none found.
     * @throws IllegalArgumentException if id is null.
     */
    fun findById(id: ID): Optional<T>?

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be null.
     * @return true if an entity with the given id exists, false otherwise.
     * @throws IllegalArgumentException if id is null.
     */
    fun existsById(id: ID): Boolean

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    fun findAll(): List<T>?

    fun count(): Long

    /**
     * Deletes the entity with the given id.
     *
     *
     * If the entity is not found in the persistence store it is silently ignored.
     *
     * @param id must not be null.
     * @throws IllegalArgumentException in case the given id is null
     */
    fun deleteById(id: ID)

    /**
     * Deletes a given entity.
     *
     * @param entity must not be null.
     * @throws IllegalArgumentException in case the given entity is null.
     * @throws OptimisticLockingFailureException when the entity uses optimistic locking and has a version attribute with
     * a different value from that found in the persistence store. Also thrown if the entity is assumed to be
     * present but does not exist in the database.
     */
    fun delete(entity: T)
    fun <S : T?> save(entity: S): S
}