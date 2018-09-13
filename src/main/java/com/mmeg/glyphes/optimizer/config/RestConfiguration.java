package com.mmeg.glyphes.optimizer.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Config Spring Security pour les services REST.
 *
 * @author mdenoual
 *
 */
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 2)
public class RestConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private WebCfgProperties webCfgProperties;


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final String restPrefix = webCfgProperties.getSecurity().getRestPrefix();

        // @formatter:off
        http
                .antMatcher(restPrefix + "/**")
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(restPrefix + "/palynologie/**",restPrefix + "/residus/**",restPrefix + "/swagger.*").permitAll();
        // @formatter:on
    }
}
