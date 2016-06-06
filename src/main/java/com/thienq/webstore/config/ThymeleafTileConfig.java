package com.thienq.webstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring4.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring4.web.view.ThymeleafTilesView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.resourceresolver.SpringResourceResourceResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 6/5/2016.
 */

@Configuration
@AutoConfigureAfter(ThymeleafAutoConfiguration.class)
@EnableConfigurationProperties(ThymeleafProperties.class)
public class ThymeleafTileConfig {


    @Autowired
    private ThymeleafProperties properties;


    @Bean
    TilesDialect tilesDialect(){
        // This bean will be auto picked-up by spring-boot and will be autoconfigured :)
        return new TilesDialect();
    }

    @Bean//create tiles configurer for your needs
    public ThymeleafTilesConfigurer tilesConfigurer(){
        final ThymeleafTilesConfigurer configurer = new ThymeleafTilesConfigurer();
        configurer.setDefinitions("classpath:/templates/tiles/definitions/tile-definition.xml");
        configurer.setCheckRefresh(!properties.isCache());
        return configurer;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setViewClass(ThymeleafTilesView.class);
        viewResolver.setTemplateEngine(springTemplateEngine());
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(tilesDialect());
        templateEngine.setAdditionalDialects(dialects);
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceResourceResolver resourceResolver(){
        SpringResourceResourceResolver resourceResolver = new SpringResourceResourceResolver();
        return resourceResolver;
    }

    @Bean
    public TemplateResolver templateResolver(){
        TemplateResolver resolver = new TemplateResolver();
        resolver.setCacheable(properties.isCache());
        resolver.setPrefix(properties.getPrefix());
        resolver.setSuffix(properties.getSuffix());
        resolver.setTemplateMode(properties.getMode());
        resolver.setResourceResolver(resourceResolver());

        return resolver;
    }

}
