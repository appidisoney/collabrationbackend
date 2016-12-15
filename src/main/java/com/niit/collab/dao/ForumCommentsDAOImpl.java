package com.niit.collab.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collab.model.ForumComments;

@Repository(value="forumCommentsDAO")

public class ForumCommentsDAOImpl implements ForumCommentsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumCommentsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean saveOrUpdate(ForumComments forumcomments) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumcomments);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean delete(ForumComments forumcomments) {
		try {
			sessionFactory.getCurrentSession().delete(forumcomments);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<ForumComments> list(int fid) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(ForumComments.class);
		c.add(Restrictions.eq("forumid", fid));
		List<ForumComments> list=c.list();
		return list;
	}
}
