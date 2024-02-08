package org.example.apps.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.apps.aop.dto.SearchRequest;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

import java.util.List;
import java.util.Set;

/**
 * @author rival
 * @since 2024-02-07
 */

@Aspect
@Component
public class SearchAspect {
    private final Set<String> forbiddenWords = Set.of("fuck","cunt");
    private final String cleanWord = "Good";

    @Pointcut("execution(* org.example.apps.aop.service.SearchService.search(..))")
    public void searchMethod() {}


    @Before("searchMethod()")
    public void beforeSearch(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        if(args.length >0 && args[0] instanceof SearchRequest req){
            sanitizeReq(req);
        }

    }

    private void sanitizeReq(SearchRequest req){
        List<String> cleanWords = req.getWords().stream().map(w -> forbiddenWords.contains(w) ? cleanWord : w).toList();
        req.setWords(cleanWords);
    }
}
