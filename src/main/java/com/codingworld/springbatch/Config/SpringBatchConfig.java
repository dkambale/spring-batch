package com.codingworld.springbatch.Config;

import com.codingworld.springbatch.bean.ShareTransaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.io.FileSystemResource;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {

  @Bean
  public Job job(JobBuilderFactory jobBuilderFactory,
      StepBuilderFactory stepBuilderFactory,
      ItemReader<ShareTransaction> itemReader,
      ItemProcessor<ShareTransaction, ShareTransaction> itemProcessor,
      ItemWriter<ShareTransaction> itemWriter
  ) {

    Step step = stepBuilderFactory.get("share-trans-step")
        .<ShareTransaction, ShareTransaction>chunk(100)
        .reader(itemReader)
        .processor(itemProcessor)
        .writer(itemWriter)
        .build();

    return jobBuilderFactory.get("share-tran")
        .incrementer(new RunIdIncrementer())
        .start(step)
        .build();
  }

  @Bean
  @StepScope
  public FlatFileItemReader<ShareTransaction> itemReader(@Value("#{jobParameters['fileName']}") String fileName) {
    System.out.println("FileName===>"+fileName);

    FlatFileItemReader<ShareTransaction> flatFileItemReader = new FlatFileItemReader<>();
    flatFileItemReader.setResource(new FileSystemResource("src/main/resources/test1.text"));
    flatFileItemReader.setName("CSV-Reader");
    flatFileItemReader.setLinesToSkip(1);
    flatFileItemReader.setLineMapper(lineMapper());
    return flatFileItemReader;
  }

  @Bean
  public LineMapper<ShareTransaction> lineMapper() {

    DefaultLineMapper<ShareTransaction> defaultLineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

    lineTokenizer.setDelimiter(",");
    lineTokenizer.setStrict(false);
    lineTokenizer.setNames("shareCode", "companyName", "qty", "amount", "type");

    BeanWrapperFieldSetMapper<ShareTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(ShareTransaction.class);

    defaultLineMapper.setLineTokenizer(lineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper);

    return defaultLineMapper;
  }

}
