package walletpasscards.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import walletpasscards.com.model.Pass;
import walletpasscards.com.model.PassResponse;
import walletpasscards.com.service.PassCardService;

@RestController
public class PassCardController {
	
	@Autowired
	PassCardService passCardService; 
	
	//-------------------Create Passcard for Policy Insurance--------------------------------------------------------
	@RequestMapping(value = "/passes/policy/", method = RequestMethod.POST)
	public ResponseEntity<PassResponse> createPolicyPass(@RequestBody Pass pass, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating pass for " + pass.getNameInsures());
		PassResponse resPass = passCardService.createPolicyPass(pass);
		if (resPass == null) {
			return new ResponseEntity<PassResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PassResponse>(resPass, HttpStatus.OK);
	}
	
	//-------------------Create Passcard for Gym Membership--------------------------------------------------------
	@RequestMapping(value = "/passes/gym/", method = RequestMethod.POST)
	public ResponseEntity<PassResponse> createGymPass(@RequestBody Pass pass, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating pass for " + pass.getNameInsures());
		PassResponse resPass = passCardService.createGymPass(pass);
		if (resPass == null) {
			return new ResponseEntity<PassResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PassResponse>(resPass, HttpStatus.OK);
	}

	//-------------------Retrieve All Passes--------------------------------------------------------
	
	@RequestMapping(value = "/passes/", method = RequestMethod.GET)
	public ResponseEntity<List<Pass>> listAllUsers() {
		List<Pass> passes = passCardService.findAllPasses();
		if(passes.isEmpty()){
			return new ResponseEntity<List<Pass>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Pass>>(passes, HttpStatus.OK);
	}
	
	
}
