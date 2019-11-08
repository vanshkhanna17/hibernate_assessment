package assessment_hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProductDao {

	private EntityManagerFactory entityManagerFactory;

	public void create(Product p) {

		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.persist(p);

		em.getTransaction().commit();
	}

	public List<Product> findAll() {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();

		return products;
	}

	public Product find(String str) {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		try {
		Product p = em.createQuery("select p from Product p where p.id= :id", Product.class).setParameter("id", str)
				.getSingleResult();
		return p;
		}
		catch(Exception e) {
			return null;
		}
	}

	public void update(Product p) {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.merge(p);

		em.getTransaction().commit();

	}

	public void delete(String id) {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		
		
		em.getTransaction().begin();
		
		Product p = em.createQuery("select p from Product p where p.id= :id", Product.class).setParameter("id", id)
				.getSingleResult();
		
		em.remove(p);

		em.getTransaction().commit();
	}

}
