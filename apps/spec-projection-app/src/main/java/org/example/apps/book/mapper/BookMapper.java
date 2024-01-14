package org.example.apps.book.mapper;

import org.example.apps.book.dto.BookCreateRequest;
import org.example.apps.book.dto.BookResponse;
import org.example.apps.book.entity.Book;
import org.example.apps.book.entity.BookKeyword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author rival
 * @since 2023-12-19
 */
@Mapper(componentModel = "spring")
public interface BookMapper {
//    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "bookKeywords", target = "keywordValues",qualifiedByName="keywordsConverter")
    BookResponse bookToBookResponse(Book entity);

    @Named("keywordsConverter")
    default List<String>  keywordsConverter(Set<BookKeyword> bookKeywords){
        return bookKeywords.stream().map(k -> k.getKeyword().getValue()).collect(Collectors.toList());
    }




    @Mappings({
        @Mapping(target="id", ignore=true),
        @Mapping(target="bookKeywords", ignore=true)
    })
    Book bookRequestToBook(BookCreateRequest bookCreateRequest);

    List<BookResponse> toResList(List<Book> entities);
}
