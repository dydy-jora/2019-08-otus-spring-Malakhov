package tst.ls_04.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import tst.ls_04.dao.DaoHelper;
import tst.ls_04.dao.DaoHelperImp;
import tst.ls_04.service.HomeWork01;
import tst.ls_04.service.HomeWork01Impl;

@PropertySource("classpath:application.yml")
@Configuration
public class ServicesConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HomeWork01 homeWork01() {
        return new HomeWork01Impl();
    }

    @Bean
    public DaoHelper helper(){
        return new DaoHelperImp();
    }

}
