package com.niit.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.Users;

@SuppressWarnings("deprecation")
@Repository(value="usersDAO")
public class UsersDAOImpl implements UsersDAO {


	@Autowired
	private SessionFactory sessionFactory;
	public UsersDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean saveOrUpdate(Users users) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(users);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Users users) {
		try {
			sessionFactory.getCurrentSession().delete(users);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Users> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Users.class);
		List<Users> list=c.list();
		return list;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<Users> getuser(String username) {
		String hql = "from Users where username= "+ "'"+ username+"'" ;
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Users>list= query.list();
		
		if(list==null)
		{
			return null;
		}
		else
		{
			return list;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public Users authuser(String username, String password) {
		String hql="from Users where username= " + "'" +username+ "'and password='" +password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Users>list=query.list();
		if(list==null)
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}
	@Transactional
	public Users oneuser(String uid) {
		String hql = "from Users where username= "+ "'"+ uid+"'" ;
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users>list= query.list();
		
		if(list==null)
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}
	@Transactional
	public Users profileof(String username) {
		String hql="from Users where username='"+username+"'";
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users>list= query.list();
		
		if(list==null)
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}
	@Transactional
	public List<Users> nonfriends(int id) {
		String hql = "from Users where id !='"+ "'" + id+"'";
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> list= query.list();
		return list;
	}
}