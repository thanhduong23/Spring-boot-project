package nhom3.spring.thanh.baitap1;

import nhom3.spring.thanh.baitap1.Service.UserDetailsServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private UserDetailsServiceIml userDetailsService;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/register", "/process-register", "/login").permitAll() // Cho phép truy cập không cần đăng nhập
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/list-users", "/companies", "/nhanviens").hasRole("USER") // Chỉ USER role mới được truy cập "/users"
                                .requestMatchers("/api/**", "/home", "/").permitAll() // Cho phép truy cập các API, trang chủ, và trang home
                                .requestMatchers("/api/register", "/api/generateToken").permitAll()
                                .requestMatchers("/api/users").hasAnyAuthority("USER", "ADMIN")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login") // Trang đăng nhập tùy chỉnh
                        .defaultSuccessUrl("/home", true) // Sau khi đăng nhập thành công, chuyển hướng đến "/home"
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")) // Chuyển hướng về trang đăng nhập sau khi đăng xuất
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }
}


//    @Bean
//    public AuthenticationManager authenticationManager(
//            UserDetailsService userDetailsService,
//            PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        return new ProviderManager(authenticationProvider);
//    }
//
//    @Bean
//    UserDetailsService userDetailsService() {
//        return new UserDetailsServiceIml();
//    }
//
//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .headers(httpSecurityHeadersConfigurer -> {
//                    httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
//                })
//                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF cho H2 Console
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/register", "/process-register", "/login").permitAll() // Cho phép truy cập không cần đăng nhập
//                        .requestMatchers("/h2-console/**").permitAll() // Cho phép truy cập H2 Console
//                        .requestMatchers("/list-users", "/companies", "/nhanviens").hasRole("USER") // Chỉ USER role mới được truy cập "/users"
//                        .requestMatchers("/api/**", "/home", "/").permitAll() // Cho phép truy cập các API, trang chủ, và trang home
//                        .requestMatchers("/api/register").permitAll()
//                        .anyRequest().authenticated()) // Các yêu cầu còn lại cần phải đăng nhập
//                .formLogin(form -> form
//                        .loginPage("/login") // Trang đăng nhập tùy chỉnh
//                        .defaultSuccessUrl("/home", true) // Sau khi đăng nhập thành công, chuyển hướng đến "/home"
//                        .permitAll())
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login")) // Chuyển hướng về trang đăng nhập sau khi đăng xuất
//                .build();
//    }



