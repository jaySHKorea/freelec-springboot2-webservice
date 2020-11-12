package com.jaySHKorea.book.springboot.config.auth;

import com.jaySHKorea.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면 사용을 위한 옵션 비활성화
                .and()
                    .authorizeRequests() // url별 권한 관리를 설정하는 옵션의 시작점, antMatchers의 필수 옵션
                    // antMatchers : 권한 관리 대상 지정 옵션, URL/HTTP 메소드별로 관리가 가능함
                    // 지정된 URL들을 permitAll을 통해 전체 열람 권한을 줌
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    // /api/v1/** 주소를 가진 API는 USER 권한을 가진 사람한테만 줌
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // 설정된 값들 이외 나머지 url(anyRequest)에 대하여는 authenticated()로 인증된 사용자들에게만 허용
                    .anyRequest().authenticated()
                .and()
                    .logout() // 로그아웃 기능에 대한 여러 설정의 진입점
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 /주소로 이동
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 설정의 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정을 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService의 인터페이스 구현체 등록
    }
}
