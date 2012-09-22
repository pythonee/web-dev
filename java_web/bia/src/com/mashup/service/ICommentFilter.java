package com.mashup.service;

import java.io.IOException;

import com.mashup.domain.Comment;

public interface ICommentFilter {
	public Comment Filter(Comment comment)throws IOException;;
}
