package curso.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable() // Desativa as configurações padrão de memória.
		.authorizeRequests() // Pertimir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuário acessa a pagina inicial
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // permite qualquer usuário
		.and().logout() // Mapeia URL de Logout e invalida usuário autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override // Cria autenticação do usuário com banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		.withUser("adm")
		.password("$2a$10$Xnjw09p8DffXlccIuqEV.OcmmdBGd71QrqmtLrvTAqiZIqa6rtky6")
		.roles("ADMIN");
		
	}
	
	@Override // Ignora URL especificas
	public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers("/materialize/**");
	}
	
	
	
}
