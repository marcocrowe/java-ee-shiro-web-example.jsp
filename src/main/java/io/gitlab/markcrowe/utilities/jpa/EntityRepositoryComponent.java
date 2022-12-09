package io.gitlab.markcrowe.utilities.jpa;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;

/**
 * Entity Repository Component
 * @param <T>
 * @param <K>
 */
@AllArgsConstructor
public class EntityRepositoryComponent<T, K>
{
	private final Class<T> entityClass;
	private final EntityManagerFactory entityManagerFactory;

    /**
     * Create a new application-managed <code>EntityManager</code>.
     * This method returns a new <code>EntityManager</code> instance each time
     * it is invoked.
     * The <code>isOpen</code> method will return true on the returned instance.
     * @return entity manager instance
     * @throws IllegalStateException if the entity manager factory
     * has been closed
     */
	public EntityManager createEntityManager()
	{
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * Delete an entity from the database
	 *
	 * @param entity The entity to delete
	 */
	public void deleteEntity(final T entity)
	{
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		try
		{
			transaction.begin();
			entityManager.remove(entity);
			transaction.commit();
		}
		catch(final Exception exception)
		{
			if(transaction.isActive())
				transaction.rollback();
			throw exception;
		}
		finally
		{
			entityManager.close();
		}
	}

	/**
	 * Get the Entity Manager Factory
	 *
	 * @return The Entity Manager Factory
	 */
	public EntityManagerFactory getEntityManagerFactory()
	{
		return entityManagerFactory;
	}

	/**
	 * Get Entity By id
	 *
	 * @param id id of Entity
	 * @return Entity
	 */
	public T getEntityById(final K id)
	{
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		try
		{
			return entityManager.find(entityClass, id);
		}
		finally
		{
			entityManager.close();
		}
	}

	/**
	 * Get all entities that match the query
	 *
	 * @param query The JPA query to execute
	 * @return A list of entities that match the query
	 */
	public List<T> getEntities(final String query)
	{
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		try
		{
			return entityManager.createQuery(query, entityClass).getResultList();
		}
		finally
		{
			entityManager.close();
		}
	}

	/**
	 * Insert or update an entity
	 *
	 * @param entity The entity to insert
	 */
	public void insertEntity(final T entity)
	{
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		try
		{
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
		}
		catch(final Exception exception)
		{
			if(transaction.isActive())
				transaction.rollback();
			throw exception;
		}
		finally
		{
			entityManager.close();
		}
	}

	/**
	 * Update an entity in the database
	 *
	 * @param entity The entity to update
	 */
	public void updateEntity(final T entity)
	{
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		try
		{
			transaction.begin();
			entityManager.merge(entity);
			transaction.commit();
		}
		catch(final Exception exception)
		{
			if(transaction.isActive())
				transaction.rollback();
			throw exception;
		}
		finally
		{
			entityManager.close();
		}
	}
}
