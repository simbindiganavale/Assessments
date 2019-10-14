package com.application.services.leavesSummaryBatch.batch;

import java.util.HashMap;
import java.util.Map;

import com.application.services.leavesSummaryBatch.domain.dao.EmployeeDAO;
import com.application.services.leavesSummaryBatch.domain.entities.Employee;
import com.application.services.leavesSummaryBatch.repository.EmployeeRepo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort.Direction;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    EmployeeRepo employeeRepo;
 
    
    @Bean
    public RepositoryItemReader<Employee>reader() {
        Map<String, Direction> sort = new HashMap<String, Direction>();
        sort.put("id", Direction.ASC);
        return new RepositoryItemReaderBuilder<Employee>()
        .repository(employeeRepo)
        .sorts(sort)
        .name("RepoReader")
        .saveState(false)
        .methodName("findAll")
        .build();
    }

    @Bean
    public EmployeeItemProcessor processor() {
        return new EmployeeItemProcessor();
    }


    @Bean
    public FlatFileItemWriter<EmployeeDAO> writer()
    {
        return new FlatFileItemWriterBuilder<EmployeeDAO>()
            .resource(new FileSystemResource("target/outputData.csv"))
            .append(true)
            .saveState(false)
            .name("FileWriter")
            .lineAggregator(new PassThroughLineAggregator<EmployeeDAO>())
            .build();
       
    }


    @Bean
    public Job exportEmployeeLeaves(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("exportEmployeeLeaves")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(FlatFileItemWriter<EmployeeDAO> writer) {
        return stepBuilderFactory.get("step1")
            .<Employee, EmployeeDAO> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }

}