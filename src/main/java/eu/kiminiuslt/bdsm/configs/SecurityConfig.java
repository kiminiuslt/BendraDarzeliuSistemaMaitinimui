package eu.kiminiuslt.bdsm.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .permitAll()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/home", true)
        .usernameParameter("loginUser")
        .passwordParameter("loginPassword")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .permitAll();
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
