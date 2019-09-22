package tst.ls_01.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import tst.ls_01.dao.DaoHelper;
import tst.ls_01.dao.DaoHelperImp;
import tst.ls_01.service.HomeWork01;
import tst.ls_01.service.HomeWork01Impl;

@PropertySource("classpath:app.properties")
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
    public HomeWork01 homeWork01(MessageSource msb, DaoHelper helper) {

        return new HomeWork01Impl(msb);
    }

    @Bean
    public DaoHelper helper(MessageSource msg){

        return new DaoHelperImp(msg);
    }
}
