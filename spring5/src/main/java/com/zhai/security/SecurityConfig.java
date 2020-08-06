package com.zhai.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder() ;
	}
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	/*内存用户存储
//	 * 
//	 * 
//	 */
////	auth.inMemoryAuthentication().withUser("buzz")
////	.password("infinity")
////	.authorities("ROLE_USER")
////	.and().
////	withUser("woody")
////	.password("bullseye")
////	.authorities("ROLE_USER");
//
//	/*基于 JDBC 的用户存储
//	 * 密码必须通过加密存贮在数据库
//	 *  BCryptPasswordEncoder —— 采用 bcrypt 强哈希加密
//NoOpPasswordEncoder —— 不应用任何编码
//Pbkdf2PasswordEncoder —— 应用 PBKDF2 加密
//SCryptPasswordEncoder —— 应用了 scrypt 散列加密
//StandardPasswordEncoder —— 应用 SHA-256 散列加密
//	 */
////
////	auth.jdbcAuthentication().dataSource(dataSource)
////	.usersByUsernameQuery("select username,password,enabled from Users"+
////	" where username=?")
////	.authoritiesByUsernameQuery("select username,authority from UserAuthorities "+ 
////			" where username=?")
////	.passwordEncoder(new BCryptPasswordEncoder());
//
///*
// * 
// * 
// * 
// * 
// */
////	 auth
////     .ldapAuthentication()
////     	.userSearchBase("ou=people")
////     	.userSearchFilter("(uid={0})")
////     	.groupSearchBase("ou=groups")
////     	.groupSearchFilter("member={0}")
////     	.passwordCompare()
////     	.passwordEncoder(new BCryptPasswordEncoder())
////     	.passwordAttribute("passcode")
////     	.contextSource()
////     	.root("dc=tacocloud,dc=com")
////     	.ldif("classpath:users.ldif");
///*
// * 自定义身份验证
// * @1-建立实体类User
// * @2-通过springdatajpa接口实现数据库查下
// * @3-建立业务层，调用repo查询用户名
// */
	

auth.userDetailsService(userDetailsService)
.passwordEncoder(encoder());
}
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
	.authorizeRequests()
		.antMatchers("/design", "/orders")
			.access("hasRole('ROLE_USERS')")
		.antMatchers("/", "/**").access("permitAll")
	
	.and()
	.formLogin()
	.loginPage("/login")
	.defaultSuccessUrl("/design", true)//可以强制用户在登录后进入设计页面，即使他们在登录之前已经在其他地方导航，方法是将 true 作为第二个参数传递给 defaultSuccessUrl：
	
	.and()
    .logout()
        .logoutSuccessUrl("/");
}
}

