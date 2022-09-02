package curso.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import curso.spring.model.Telefone;
import curso.spring.model.Usuario;
import curso.spring.repository.TelefoneRepository;
import curso.spring.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	// INICIO
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public ModelAndView inicio(){
		
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		// listar usuarios
		mv.addObject("listaUsuario", usuarioRepository.findAll());
		
		// usuario edicao
		mv.addObject("usuarioeditar", new Usuario());
		
		return mv;
	}
	
	// SALVAR
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuario")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			
			// Mensagens de erro
			List<String> msg = new ArrayList<String>();
			
			// adicionando no array
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
			
			
				ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
				
				// mensagem de erro
				mv.addObject("msg",msg);
				
				// listar usuarios
				mv.addObject("listaUsuario", usuarioRepository.findAll());
				
				// usuario edicao
				mv.addObject("usuarioeditar", usuario);
				
				return mv;
			
		}else {
			usuarioRepository.save(usuario);
			return listarUsuarios();
		}
		
		
		
		
	}
	
	
	// LISTAR USUARIOS
	@RequestMapping(method = RequestMethod.GET, value = "/listarusuarios")
	public ModelAndView listarUsuarios(){
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		// listar usuarios
		mv.addObject("listaUsuario", usuarioRepository.findAll());
		
		// usuario edicao
		mv.addObject("usuarioeditar", new Usuario());
		
		return mv;
	}
	
	
	// EDITAR
	@GetMapping(value = "/editarusuario/{idusuario}")
	public ModelAndView editarUsuario(@PathVariable(value = "idusuario") Long idUsuario){
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		// usuario edicao
		mv.addObject("usuarioeditar", usuarioRepository.findById(idUsuario).get()); 
		
		// listar usuarios
		mv.addObject("listaUsuario", usuarioRepository.findAll());
		
		
		
		return mv;
	}
	
	// DELETAR
	@GetMapping(value = "/excluirusuario/{idusuario}")
	public ModelAndView excluirUsuario(@PathVariable(value = "idusuario") Long idUsuario){
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		usuarioRepository.deleteById(idUsuario);
		
		// listar usuarios
		mv.addObject("listaUsuario", usuarioRepository.findAll());
		
		// usuario edicao
		mv.addObject("usuarioeditar", new Usuario());
		
		return mv;
	}
	
	
	// PESQUISAR USUARIO POR NOME
	@PostMapping( value = "/pesquisarusuario")
	public ModelAndView pesquisarUsuarioByName(@RequestParam(value = "campoPesquisar") String campoPesquisar){
		
		ModelAndView mv = new ModelAndView("cadastro/cadastrousuario");
		
		// listar usuarios pesquisados
		mv.addObject("listaUsuario", usuarioRepository.pesquisarByNome(campoPesquisar));
		
		// usuario edicao
		mv.addObject("usuarioeditar", new Usuario());
		
		return mv;
	}
	
	// LISTA TELEFONES
	@GetMapping(value = "/telefones/{idusuario}")
	public ModelAndView telefonesUsuario(@PathVariable("idusuario") Long idUsuario){
		
		ModelAndView mv = new ModelAndView("cadastro/telefones");
		
		// usuario edicao
		mv.addObject("usuarioeditar", usuarioRepository.findById(idUsuario).get());
		
		// lista telefone
		mv.addObject("listaTelefone",telefoneRepository.pesquisarTelefoneByidUsuario(idUsuario));
		
		return mv;
	}
	
	// SALVAR TELEFONE
	@PostMapping(value = "/addfonepessoa/{idusuario}")
	public ModelAndView addFonePessoa(Telefone telefone, @PathVariable("idusuario") Long idUsuario){
		
		Usuario u = usuarioRepository.findById(idUsuario).get();
		
		// Se vazio
		if(telefone != null && telefone.getNumero().isEmpty()) {
			
			ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
			modelAndView.addObject("usuarioeditar", u);
			modelAndView.addObject("listaTelefone", telefoneRepository.pesquisarTelefoneByidUsuario(idUsuario));
			
			
			// msg vazio
			List<String> msg = new ArrayList<String>();
			if (telefone.getNumero().isEmpty()) {
				msg.add("Numero deve ser informado");
			}
			 
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
			
		}
		
		
		
		ModelAndView mv = new ModelAndView("cadastro/telefones");
		
		// salvando telefone
		telefone.setUsuario(u);
		telefoneRepository.save(telefone);
		
		// usuario edicao
		mv.addObject("usuarioeditar", u);
		
		// lista telefone
		mv.addObject("listaTelefone",telefoneRepository.pesquisarTelefoneByidUsuario(idUsuario));
				
				
		
		return mv;
	}
	
	// DELETAR TELEFONE
	@GetMapping(value = "/excluirtelefone/{idtelefone}")
	public ModelAndView excluirTelefone(@PathVariable("idtelefone") Long idTelefone){
		
		ModelAndView mv = new ModelAndView("cadastro/telefones");
		
		Usuario u = telefoneRepository.findById(idTelefone).get().getUsuario();
		
		// deletar telefone
		telefoneRepository.deleteById(idTelefone);
		
		// usuario edicao
		mv.addObject("usuarioeditar",u);
		
		// lista telefone
		mv.addObject("listaTelefone",telefoneRepository.pesquisarTelefoneByidUsuario(u.getId()));
			
		
		return mv;
	}
	
	
	
	 
	
	
}
