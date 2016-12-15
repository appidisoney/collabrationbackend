package com.niit.collab.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collab.dao.ForumCommentsDAO;
import com.niit.collab.model.ForumComments;

@RestController
public class ForumCommentsController {

		@Autowired
		private ForumCommentsDAO forumCommentsDAO;
		
		@PostMapping(value="/commentforum/{fid}")
		public ResponseEntity<ForumComments> forumcomments(@RequestBody ForumComments forumcomments,HttpSession session,@PathVariable("fid") int fid){
			int uid=(Integer) session.getAttribute("uid");
			forumcomments.setForumid(fid);
			forumcomments.setUserid(uid);
			forumcomments.setCommenttime(new Date());
			forumCommentsDAO.saveOrUpdate(forumcomments);
			return new ResponseEntity<ForumComments>(forumcomments,HttpStatus.OK);
			
		}
		
		@GetMapping(value="/getforumcomments/{fid}")
		public ResponseEntity<List<ForumComments>> getcomments(@PathVariable("fid") int fid){
			List<ForumComments> comments =forumCommentsDAO.list(fid);
			return new ResponseEntity<List<ForumComments>>(comments,HttpStatus.OK);
		}
	}

