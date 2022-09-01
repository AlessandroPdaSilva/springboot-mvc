package curso.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import curso.spring.model.Usuario;
import curso.spring.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public String inicio(){
		return "cadastro/cadastrousuario";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public String salvar(Usuario usuario){
		
		usuarioRepository.save(usuario);
		
		return "cadastro/cadastrousuario";
	}
	
	
	
}
