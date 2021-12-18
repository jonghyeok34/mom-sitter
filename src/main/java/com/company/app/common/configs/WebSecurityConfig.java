package com.company.app.common.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Value("${secret.password}")
    private String passwordSecret;
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOriginPatterns(CorsConfiguration.ALL)
                                          .allowedMethods(CorsConfiguration.ALL)
                                          .allowedHeaders(CorsConfiguration.ALL)
                                          .allowCredentials(true)
                                          .maxAge(50000);
			}
		};
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        // encoding 기본형
        String idForEncode = "pbkdf2";
        System.out.println("secretKey:"+ passwordSecret);
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder(passwordSecret));
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/**");
        // web.ignoring().antMatchers("/users/sign-up", "/users/sign-in");
    }

    
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     // cors 전체 열기 - 테스트용
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.addAllowedOriginPattern(CorsConfiguration.ALL);
    //     configuration.addAllowedMethod(CorsConfiguration.ALL);
    //     configuration.addAllowedHeader(CorsConfiguration.ALL);
    //     configuration.setAllowCredentials(true);

    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", configuration);

    //     http.httpBasic().disable()
    //         .csrf().disable()
    //         .cors().configurationSource(source)
    //         .and().headers().frameOptions();// 

    //     http.authorizeRequests()
    //             .antMatchers("/api/**").permitAll();

    //     // http.authorizeRequests()
    //     //     .and()
    //     //         .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler())
    //     //     .and()
    //     //         .addFilterBefore(jwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

    // }
}
