package com.jdonee.cookie.optional_with_jackson;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单书籍对象
 * @author Frank Zeng
 *
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Author {
	
	//姓
	String lastName;
	
	//名
	String firstName;
	
	//年龄
	Integer age;
}
