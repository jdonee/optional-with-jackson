package com.jdonee.cookie.optional_with_jackson;

import java.util.Optional;

import lombok.Data;

/**
 * 简单书籍对象
 * @author Frank Zeng
 *
 */
@Data
public class Book {
	
	//标题
	String title;
	
	//副标题
	Optional<String> subTitle;
}
