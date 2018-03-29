package com.jdonee.cookie.optional_with_jackson;

import java.util.List;
import java.util.Optional;

import lombok.Data;

/**
 * 复杂书籍对象
 * @author Frank Zeng
 *
 */
@Data
public class Book2 {
	
	//标题
	String title;
	
	//副标题
	Optional<String> subTitle;
	
	List<Optional<Author>> authors;
	
}
