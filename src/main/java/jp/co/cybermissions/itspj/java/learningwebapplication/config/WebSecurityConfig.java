package jp.co.cybermissions.itspj.java.learningwebapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.cybermissions.itspj.java.learningwebapplication.models.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests().antMatchers("/**").permitAll();
    //     http.csrf().disable();
    //     http.headers().frameOptions().disable();
    // }


    private final UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //対象外のとこ(staticfile)
        web.ignoring().antMatchers("/js/**","/css/**","/webjars/**");
    }

    /**Httpリクエストごとのセキュリティ */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        //ログイン画面と登録画面は本番でもいる
        .antMatchers("/login","/register").permitAll()
        .antMatchers("/").permitAll()
        //h2コンソール用,本番で削除
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/teacher/**").hasRole(Role.TEACHER.name())
        // .antMatchers("/student/**").hasRole(Role.STUDENT.name())
        .anyRequest().authenticated().and()

        .formLogin()
        .loginPage("/login")
        //teacherとstudentで今は分けてるため改訂必須
        .defaultSuccessUrl("/home").and()
        .logout()
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/");

        //下記はh2-console用、本番では消す
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }
}