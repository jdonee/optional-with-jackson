package com.jdonee.cookie.optional_with_jackson;

import static io.restassured.path.json.JsonPath.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@RunWith(JUnitPlatform.class)
class OptionalTypeUnitTest {

	ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());

    @Test
    public void givenPresentOptional_whenSerializing_thenValueInJson() throws JsonProcessingException {
        String subTitle = "Hello World!";
        Book book = new Book();
        book.setTitle("Java 8");
        book.setSubTitle(Optional.of(subTitle));
        String result = mapper.writeValueAsString(book);
        assertThat(from(result).getString("subTitle"), is(equalTo(subTitle)));
    }

    @Test
    public void givenEmptyOptional_whenSerializing_thenNullValue() throws JsonProcessingException {
        Book book = new Book();
        book.setTitle("Java 8");
        book.setSubTitle(Optional.empty());
        String result = mapper.writeValueAsString(book);
        assertThat(from(result).getString("subTitle"),is(equalTo(null)));
    }

    @Test
    public void givenField_whenDeserializingIntoOptional_thenIsPresentWithValue() throws IOException {
        String subTitle = "Hello World!";
        String book = "{ \"title\": \"Java 8\", \"subTitle\": \"" + subTitle + "\" }";
        Book result = mapper.readValue(book, Book.class);
        assertThat(result.getSubTitle(), is(equalTo(Optional.of(subTitle))));
    }

    @Test
    public void givenNullField_whenDeserializingIntoOptional_thenIsEmpty() throws IOException {
        String book = "{ \"title\": \"Java 8\", \"subTitle\": null }";
        Book result = mapper.readValue(book, Book.class);
        assertThat(result.getSubTitle(),is(equalTo(Optional.empty())));
    }

}
