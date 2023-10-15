package org.example.crudprogramveb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("org.example.crudprogramveb")
@EnableWebMvc
public class NotesConfig implements WebMvcConfigurer { // конфигурационный класс

    private final ApplicationContext applicationContext; // создаём ApplicationContext

    @Autowired
    public NotesConfig(ApplicationContext applicationContext) { // создаём конструктор
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() { // метод создаёт правило, как Spring будет находить и читать html-шаблоны
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver(); // создаём объект класса SpringResourceTemplateResolver
        templateResolver.setApplicationContext(applicationContext); // вызывает метод setApplicationContext()
        templateResolver.setPrefix("/WEB-INF/views/"); // где искать шаблоны
        templateResolver.setSuffix(".html"); // что они из себя представляют
        return templateResolver; // возвращаем объект класса
    }

    @Bean
    public SpringTemplateEngine templateEngine() { // метод создаёт машину для обработки html-шаблонов
        SpringTemplateEngine templateEngine = new SpringTemplateEngine(); // создаём объект класса SpringTemplateEngine
        templateEngine.setTemplateResolver(templateResolver()); // вызывает метод setTemplateResolver()
        templateEngine.setEnableSpringELCompiler(true); //вызываем метод setEnableSpringELCompiler(true), чтобы запустить компилятор
        return templateEngine; // возвращаем машину
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) { // метод, сообщающий какой движок надо использовать для отображения web-страницы
        ThymeleafViewResolver resolver = new ThymeleafViewResolver(); // создаём объект класса ThymeleafViewResolver
        resolver.setTemplateEngine(templateEngine()); // устанавливаем в методе setTemplateEngine() машину
        registry.viewResolver(resolver); // связывает машину с механизмом, превращающим шаблоны в конечные страницы
    }
}
