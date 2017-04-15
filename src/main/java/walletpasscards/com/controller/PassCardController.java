package walletpasscards.com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import walletpasscards.com.model.Pass;
import walletpasscards.com.model.PassResponse;
import walletpasscards.com.model.PassUpdateResponse;
import walletpasscards.com.model.Socio;
import walletpasscards.com.service.PassCardService;
import walletpasscards.com.service.SocioService;

@RestController
public class PassCardController {

	public static final int PASSLOT_API_RESPONSE_STATUS_SUCESS = 201;

	@Autowired
	PassCardService passCardService;

	private SocioService socioService;

	@Autowired(required = true)
	@Qualifier(value = "socioService")
	public void setPersonService(SocioService ss) {
		this.socioService = ss;
	}

	// -------------------Create Passcard for Gym
	// Membership--------------------------------------------------------
	@RequestMapping(value = "/passes/gym/", method = RequestMethod.POST)
	public ResponseEntity<PassResponse> createGymPass(@RequestBody Pass pass, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando pass para socio: " + pass.getNombreSocio());
		PassResponse resPass = passCardService.createGymPass(pass);
		if (resPass == null) {
			return new ResponseEntity<PassResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (resPass.getStatus() == PASSLOT_API_RESPONSE_STATUS_SUCESS) {
			Socio newsocio = new Socio();
			newsocio.setDni(pass.getDni());
			newsocio.setNroSocio(pass.getNroSocio());
			newsocio.setEmail(pass.getEmail());
			newsocio.setEstado(pass.getEstado());
			newsocio.setSerialNumber(resPass.getSerialNumber());
			newsocio.setCantClases(Integer.valueOf(pass.getCantClases()));
			newsocio.setFechaVto(pass.getFechaVto());
			newsocio.setFechaCreacion(new Date());
			newsocio.setFechaUpdate(new Date());
			socioService.addPerson(newsocio);
		}
		return new ResponseEntity<PassResponse>(resPass, HttpStatus.OK);
	}

	// Update Passcard for Gym
	@RequestMapping(value = "/passes/gym/update/", method = RequestMethod.POST)
	public ResponseEntity<PassUpdateResponse> updateGymPass(@RequestBody Pass pass, UriComponentsBuilder ucBuilder) {
		System.out.println("uctualizando pass para socio: " + pass.getEstado());
		String dni = pass.getDni();
		if (dni == null) {
			return new ResponseEntity<PassUpdateResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Socio socio = socioService.findSerialNumberByDNI(dni);
		if (socio != null) {
			pass.setSerialNumber(socio.getSerialNumber());
			PassUpdateResponse resPass = passCardService.updateGymPass(pass);
			if (resPass == null) {
				return new ResponseEntity<PassUpdateResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			socio.setEstado(resPass.getEstado());
			if(resPass.getCantClases() != null) {
				socio.setCantClases(Integer.valueOf(resPass.getCantClases()));
			}
			if(resPass.getFechaVto() != null && !resPass.getFechaVto().isEmpty()) {
				socio.setFechaVto(resPass.getFechaVto());
			}
			socioService.updatePerson(socio);
			return new ResponseEntity<PassUpdateResponse>(resPass, HttpStatus.OK);
		}
		
		return new ResponseEntity<PassUpdateResponse>(HttpStatus.NO_CONTENT);
	}

	// Passes--------------------------------------------------------
	@RequestMapping(value = "/passes/", method = RequestMethod.GET)
	public ResponseEntity<List<PassResponse>> listAllPasses() {
		JSONArray passes = passCardService.findAllPasses();
		if (passes == null) {
			return new ResponseEntity<List<PassResponse>>(HttpStatus.NO_CONTENT);
		}
		List<PassResponse> response = getPassesresponseFromJSONObject(passes);
		return new ResponseEntity<List<PassResponse>>(response, HttpStatus.OK);
	}

	private List<PassResponse> getPassesresponseFromJSONObject(JSONArray passes) {
		List<PassResponse> resPasses = new ArrayList<PassResponse>();

		for (int i = 0; i < passes.length(); i++) {
			PassResponse passJson = new PassResponse();
			JSONObject row = passes.getJSONObject(i);
			passJson.setSerialNumber(row.getString("serialNumber"));
			passJson.setPassTypeIdentifier(row.getString("passType"));
			resPasses.add(passJson);
		}

		return resPasses;
	}

}
