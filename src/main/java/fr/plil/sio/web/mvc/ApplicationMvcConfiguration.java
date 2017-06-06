package fr.plil.sio.web.mvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class ApplicationMvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMvcConfiguration.class);

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode. TARGET_CLASS)
    public UserSession userSession() {
        logger.debug("new user session bean");
        return new UserSession();
    }

/*
    @Bean
    public CheckUserInterceptor checkUserInterceptor() {
        return new CheckUserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CheckUserInterceptor interceptor = checkUserInterceptor();
        interceptor.setUserSession(userSession());
        registry.addInterceptor(interceptor);
    }
*/
}
