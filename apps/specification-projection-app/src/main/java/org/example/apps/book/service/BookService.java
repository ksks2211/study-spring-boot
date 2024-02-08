package org.example.apps.book.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.apps.book.dto.BookCreateRequest;
import org.example.apps.book.dto.BookResponse;
import org.example.apps.book.dto.BookSearchRequest;
import org.example.apps.book.entity.Book;
import org.example.apps.book.entity.Keyword;
import org.example.apps.book.mapper.BookMapper;
import org.example.apps.book.repository.BookRepository;
import org.example.apps.book.repository.BookSearchProjection;
import org.example.apps.book.repository.KeywordRepository;
import org.example.apps.book.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rival
 * @since 2024-01-03
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final KeywordRepository keywordRepository;


    @Transactional
    public BookResponse createBook(BookCreateRequest bookCreateRequest){
        Book book = bookMapper.bookRequestToBook(bookCreateRequest);

        for(String value: bookCreateRequest.getKeywordValues()){
            Keyword keyword = keywordRepository.findByValue(value).orElseGet(() -> keywordRepository.save(Keyword.builder().value(value).build()));
            book.addKeyword(keyword);
        }
        Book saved = bookRepository.save(book);

        return bookMapper.bookToBookResponse(saved);
    }

    @Transactional
    public BookResponse findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return bookMapper.bookToBookResponse(book);
    }


    @Transactional
    public  Page<BookSearchProjection> findBooks(BookSearchRequest request){

        Specification<Book> spec = Specification.where(null);

        if(request!=null) {
            if(request.getPrefix()!=null){
                spec = spec.and(BookSpecification.titleStartsWith(request.getPrefix()));
            }
            if(request.getKeywordValues()!=null){
                List<String> keywordValues = request.getKeywordValues();
                for (var keyword : keywordValues) {
                    spec = spec.and(BookSpecification.haveTag(keyword));
                }
            }
        }

        PageRequest pageRequest = PageRequest.of(0, 10);
        return bookRepository.searchAllBooks(spec, pageRequest);
    }


}
