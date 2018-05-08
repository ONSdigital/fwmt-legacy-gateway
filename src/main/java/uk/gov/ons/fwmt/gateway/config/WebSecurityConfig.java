package uk.gov.ons.fwmt.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_WHITELIST = {

      // -- swagger ui
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**", 
      "/info"
  };

  @Autowired
  private BasicAuthenticationPoint basicAuthenticationPoint;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
  }
}

