package com.graphqljava.bookdetails.datafetchers;

import com.google.common.collect.ImmutableMap;
import graphql.execution.DataFetcherResult;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "title", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1",
                    "bookStores", "store-1,store-3"),
            ImmutableMap.of("id", "book-2",
                    "title", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2",
                    "bookStores", "store-2"),
            ImmutableMap.of("id", "book-3",
                    "title", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    private static List<Map<String, String>> stores = Arrays.asList(
            ImmutableMap.of("id", "store-1",
                    "storeName", "ABC Bookstore",
                    "storeLocation", "Parnell St"),
            ImmutableMap.of("id", "store-2",
                    "storeName", "Rockstone Bookshop",
                    "storeLocation", "Dublin Castle"),
            ImmutableMap.of("id", "store-3",
                    "storeName", "City Books",
                    "storeLocation", "Grafton St")
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            final Map<String, String> localContext = new HashMap<>();
            localContext.put("reason", dataFetchingEnvironment.getArgument("reason"));

            final String bookId = dataFetchingEnvironment.getArgument("id");
            Map<String, String> bookDetails = books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst().orElse(null);

            return DataFetcherResult.newResult()
                    .data(bookDetails)
                    .localContext(localContext)
                    .build();
        };
    }

    public DataFetcher listBooks() {
        return dataFetchingEnvironment -> {
            final Integer limit = dataFetchingEnvironment.getArgument("limit");
            if(limit != null) {
                return books
                        .stream()
                        .limit(limit.longValue())
                        .map(book -> new HashMap<String, String>() {{
                            put("id", book.get("id"));
                            put("title", book.get("title"));
                        }})
                        .collect(Collectors.toList());
            }
            return books;
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            final Map<String, String> book = dataFetchingEnvironment.getSource();
            final String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst().orElse(null);
        };
    }

    public DataFetcher getBookStores() {
        return dataFetchingEnvironment -> {
            final Map<String, String> book = dataFetchingEnvironment.getSource();
            if(book.get("bookStores") != null){
                final List<String> bookStores = Arrays.asList(book.get("bookStores").split(","));
                return stores
                        .stream()
                        .filter(store -> bookStores.contains(store.get("id")))
                        .collect(Collectors.toList());
            } else {
                return null;
            }
        };
    }
}
