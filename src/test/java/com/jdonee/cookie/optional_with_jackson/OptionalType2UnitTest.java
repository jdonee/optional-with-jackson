package com.jdonee.cookie.optional_with_jackson;

import static io.restassured.path.json.JsonPath.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

class OptionalType2UnitTest {

	ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());

    @Test
    public void givenPresentOptional_whenSerializing_thenValueInJson() throws JsonProcessingException {
        String subTitle = "Hello World!";
        Book2 book = new Book2();
        book.setTitle("Java 8");
        book.setSubTitle(Optional.of(subTitle));
        Author author = Author.builder().firstName("Frank").lastName("Zeng").age(28).build();
        List<Optional<Author>> authors=new ArrayList<>();
        authors.add(Optional.of(author));
        book.setAuthors(authors);
        String result = mapper.writeValueAsString(book);
        assertThat(from(result).getString("authors[0].age"), is(equalTo("28")));
        assertThat(from(result).getString("authors[0].firstName"), is(equalTo("Frank")));
        assertThat(from(result).getString("authors[0].lastName"), is(equalTo("Zeng")));
    }

    @Test
    public void givenEmptyOptional_whenSerializing_thenNullValue() throws JsonProcessingException {
        Book2 book = new Book2();
        book.setTitle("Java 8");
        book.setSubTitle(Optional.empty());
        List<Optional<Author>> authors=new ArrayList<>();
        authors.add(Optional.empty());
        book.setAuthors(authors);
        String result = mapper.writeValueAsString(book);
        assertThat(from(result).getString("authors[0]"),is(equalTo(null)));
    }

    @Test
    public void givenField_whenDeserializingIntoOptional_thenIsPresentWithValue() throws IOException {
        String firstName = "Frank";
        String book = "{\"title\":\"Java 8\",\"subTitle\":\"Hello World!\",\"authors\":[{\"lastName\":\"Zeng\",\"firstName\":\""+firstName+"\",\"age\":28}]}";
        Book2 result = mapper.readValue(book, Book2.class);
        assertThat(result.getAuthors().get(0).get().getFirstName(), is(equalTo(firstName)));
    }

    @Test
    public void givenNullField_whenDeserializingIntoOptional_thenIsEmpty() throws IOException {
        String book = "{\"title\":\"Java 8\",\"subTitle\":null,\"authors\":[null]}";
        Book2 result = mapper.readValue(book, Book2.class);
        assertThat(result.getAuthors().size(),is(equalTo(1)));
    }

}
