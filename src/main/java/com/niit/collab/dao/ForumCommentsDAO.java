package com.niit.collab.dao;

import java.util.List;

import com.niit.collab.model.ForumComments;

public interface ForumCommentsDAO {
	
	public boolean saveOrUpdate(ForumComments forumcomments);
	public boolean delete(ForumComments forumcomments);
	public List<ForumComments> list(int fid);
}
