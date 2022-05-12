package com.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
public class TilesConfig {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();

        tilesConfigurer.setDefinitions("/WEB-INF/template/tiles.xml");
        tilesConfigurer.setCheckRefresh(true);

        return tilesConfigurer;
    }

//    @Bean
//    public UrlBasedViewResolver tilesResolver() {
//        UrlBasedViewResolver tilesResolver = new UrlBasedViewResolver();
//
//        tilesResolver.setViewClass(TilesView.class);
//
//        return tilesResolver;
//    }
}
