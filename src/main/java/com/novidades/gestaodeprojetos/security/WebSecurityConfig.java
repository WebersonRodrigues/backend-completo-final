package com.novidades.gestaodeprojetos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Aqui informo que é uma classe de segurança do WebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    
    /*
        Metodo que devolve a instância do objeto que sabe devolver o nosso padrão de codificação.
        Isso não tem nada a ver com o JWT.
        Aqui será usado para codificar a senha do usuario gerando um hash.    
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Metodo padrão pra configurar o nosso custom com o nosso metodo de codificar senha.
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    //Metodo padrão: Esse metodo é obrigatório para conseguirmos trabalhar com a autenticação no login.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Metodo que tem a configuração global de acessos e permissoes por rotas.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Parte padrão da configuração, por enquanto ignorar.
        http
            .cors().and().csrf().disable()
            .exceptionHandling()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()

            /*
                Daqui pra baixo é onde nos vamos futucar e fazer nossas validações.
                Aqui vamos informar as rotas que não vão precisar de autenticação.
            */
            .antMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/login" )
			.permitAll() // informa que todos podem acessar, não precisa de autenticação.

            .anyRequest()
            .authenticated();// Digo que as demais requisições devem ser autenticadas.

            // Aqui eu informo que antes de qualter requisição http, o sistema deve usar o nosso filtro jwtAuthenticationFilter.
            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
