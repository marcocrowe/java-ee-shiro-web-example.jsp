package io.gitlab.markcrowe.repositories;

import io.gitlab.markcrowe.User;
import io.gitlab.markcrowe.utilities.jpa.EntityRepositoryComponent;
import java.util.List;
import javax.persistence.*;

public class UserRepository
{
	private final EntityRepositoryComponent<User, Integer> entityRepositoryComponent;

	public UserRepository(String  persistenceUnitName)
	{
		entityRepositoryComponent = new EntityRepositoryComponent<>(User.class, Persistence.createEntityManagerFactory(persistenceUnitName));
	}

	public List<User> getAllUsers()
	{
		final String query = "SELECT u from User u";
		return entityRepositoryComponent.getEntities(query);
	}

	public User getUserById(final int userId)
	{
		return entityRepositoryComponent.getEntityById(userId);
	}

	public void insertUser(final User user)
	{
		entityRepositoryComponent.insertEntity(user);
	}

	public void updateUser(final User user)
	{
		entityRepositoryComponent.updateEntity(user);
	}

	public User getUserByCredentials(String username, String password)
	{
		final EntityManager entityManager = entityRepositoryComponent.createEntityManager();
		try
		{
			final String query = "SELECT u from User u Where u.userName = :username AND u.password = :password";
			return entityManager.createQuery(query, User.class)
					.setParameter("username", username)
					.setParameter("password", password)
					.getResultList()
					.stream().findFirst().orElse(null);
		}
		finally
		{
			entityManager.close();
		}
	}
}
