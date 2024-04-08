package br.com.campelo.marcos.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.campelo.marcos.exception.resolver.BusinessExceptionResolver;
import br.com.campelo.marcos.exception.resolver.ConstraintViolationResolver;
import br.com.campelo.marcos.exception.resolver.ExceptionResolver;
import br.com.campelo.marcos.exception.resolver.MethodArgumentNotValidExceptionResolver;

@Configuration
public class ExceptionConfig {

  @Bean
  public BeanFactoryPostProcessor beanFactoryPostProcessor() {
    return factory -> {
      final BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;

      registry.registerBeanDefinition("businessExceptionResolver", BeanDefinitionBuilder
          .genericBeanDefinition(BusinessExceptionResolver.class).getBeanDefinition());

      registry.registerBeanDefinition("constraintViolationResolver", BeanDefinitionBuilder
          .genericBeanDefinition(ConstraintViolationResolver.class).getBeanDefinition());

      registry.registerBeanDefinition("methodArgumentNotValidExceptionResolver", BeanDefinitionBuilder
          .genericBeanDefinition(MethodArgumentNotValidExceptionResolver.class).getBeanDefinition());
            
      registry.registerBeanDefinition("exceptionResolver",
          BeanDefinitionBuilder.genericBeanDefinition(ExceptionResolver.class).getBeanDefinition());
    };
  }
}
