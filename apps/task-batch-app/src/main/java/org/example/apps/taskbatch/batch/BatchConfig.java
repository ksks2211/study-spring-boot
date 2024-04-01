package org.example.apps.taskbatch.batch;

import lombok.RequiredArgsConstructor;
import org.example.apps.taskbatch.Todo;
import org.example.apps.taskbatch.TodoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rival
 * @since 2024-02-17
 */
@Configuration
@RequiredArgsConstructor
public class BatchConfig  {

    private final TodoRepository todoRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public ListItemReader<Todo> reader() {
        List<Todo> todos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            todos.add(Todo.builder().task("Task "+i).build());
        }
        return new ListItemReader<>(todos);
    }
    @Bean
    public ItemProcessor<Todo, Todo> processor() {
        return todo -> {
            // Here you can process your Todo item if needed
            return todo;
        };
    }


    @Bean
    public RepositoryItemWriter<Todo> writer() {
        RepositoryItemWriter<Todo> writer = new RepositoryItemWriter<>();
        writer.setRepository(todoRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(JobRepository jobRepository){
        return new StepBuilder("step1",jobRepository)
            .<Todo,Todo>chunk(10,transactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
    }

    @Bean
    public Job createTodoJob(JobRepository jobRepository, Step step){
        return new JobBuilder("createTodoJob",jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();
    }
}
