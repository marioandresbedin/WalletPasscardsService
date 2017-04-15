package walletpasscards.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import walletpasscards.com.model.Socio;
import walletpasscards.com.service.SocioService;

@Controller
public class SocioController {

	private SocioService socioService;

	@Autowired(required = true)
	@Qualifier(value = "socioService")
	public void setPersonService(SocioService ss) {
		this.socioService = ss;
	}

	@RequestMapping(value = "/socios", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("socio", new Socio());
		model.addAttribute("listSocios", this.socioService.listSocios());
		return "socio";
	}

	// For add and update person both
	@RequestMapping(value = "/socio/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("socio") Socio s) {

		if (s.getId() == 0) {
			// new person, add it
			this.socioService.addPerson(s);
		} else {
			// existing person, call update
			this.socioService.updatePerson(s);
		}

		return "redirect:/socios";

	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id) {

		this.socioService.removeSocio(id);
		return "redirect:/socios";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", this.socioService.getSocioById(id));
		model.addAttribute("listSocios", this.socioService.listSocios());
		return "socio";
	}

}
