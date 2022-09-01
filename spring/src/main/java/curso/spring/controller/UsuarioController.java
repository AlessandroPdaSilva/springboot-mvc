package curso.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import curso.spring.model.Usuario;
import curso.spring.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// INICIO
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public ModelAndView inicio(){
		
		// listar usuarios
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		mv.addObject("usuarioeditar", new Usuario());
		
		return mv;
	}
	
	// SALVAR
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public ModelAndView salvar(Usuario usuario){
		
		usuarioRepository.save(usuario);
		
		return listarUsuarios();
	}
	
	
	// LISTAR USUARIOS
	@RequestMapping(method = RequestMethod.GET, value = "/listarusuarios")
	public ModelAndView listarUsuarios(){
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		// listar usuarios
		Iterable<Usuario> u = usuarioRepository.findAll();
		mv.addObject("listaUsuario", u);
		
		// usuario edicao
		mv.addObject("usuarioeditar", new Usuario());
		
		return mv;
	}
	
	
	// EDITAR
	@GetMapping(value = "/editarusuario/{idusuario}")
	public ModelAndView editarUsuario(@PathVariable(value = "idusuario") Long idUsuario){
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		// usuario edicao
		Optional<Usuario> aux = usuarioRepository.findById(idUsuario);
		Usuario u = aux.get();
		mv.addObject("usuarioeditar", u); 
		
		// listar usuarios
		Iterable<Usuario> listaUsuario = usuarioRepository.findAll();
		mv.addObject("listaUsuario", listaUsuario);
		
		
		
		return mv;
	}
	
}
