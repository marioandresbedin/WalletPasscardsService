package walletpasscards.com.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import walletpasscards.com.model.Pass;
import walletpasscards.com.model.PassResponse;
import walletpasscards.com.model.PassUpdateResponse;
import walletpasscards.com.util.PassesUtil;



@Service("passCardService")
@Transactional
public class PassCardServiceImpl implements PassCardService {
	
	public static final String PASSLOT_API_URL_BASE = "https://api.passslot.com/v1/templates/";
	public static final int PASSLOT_API_RESPONSE_STATUS_SUCESS = 200;
	public static final String PASSLOT_API_AUTH = "Basic ellkV0NrYkpLZk9DeEFnUllBd0FZcGNvWnVqSGFaZ2hVRUxVdWhnTWdVUG9ETXBtbVpXdVdtcm5EWmNJdEJXRzo=";
	public static final String PASSLOT_API_CONTENT_TYPE = "application/json";
	public static final String PASSLOT_API_PASS_TYPE_ID = "pass.insurance.demo";


	@Override
	public Pass findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pass findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PassResponse createGymPass(Pass pass) {
		HttpResponse<JsonNode> response = null;
		try {
			JSONObject json = new JSONObject();
			json.put("nombreSocio", pass.getNombreSocio());    
			json.put("nroSocio", pass.getNroSocio());    
			json.put("estado", pass.getEstado());    
			json.put("clases", pass.getClases()); 
			json.put("cantClases", pass.getCantClases());    
			json.put("fechaVto", pass.getFechaVto());    

			// https://api.passslot.com/v1/templates/:{idTemplate}/pass
			response = Unirest.post("https://api.passslot.com/v1/templates/5681803151278080/pass")
					.header("Authorization", PASSLOT_API_AUTH)
					.header("Content-Type", PASSLOT_API_CONTENT_TYPE).body(json.toString())
					.asJson();
			if(response.getStatus() == PASSLOT_API_RESPONSE_STATUS_SUCESS ){
				// Guardar info en la DB
				

			}
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PassResponse result = getPassByResponse(response);
		return result;
	}
	
	@Override
	public PassUpdateResponse updateGymPass(Pass pass) {
		HttpResponse<JsonNode> response = null;
		HttpResponse<JsonNode> statusResponse = null;

		try {
			JSONObject updateJson = new JSONObject();
			String serialNumber = pass.getSerialNumber();
			updateJson = PassesUtil.getJsonFromPassCard(pass);
			// PUT https://api.passslot.com/v1/passes/:passTypeIdentifier/:serialNumber/values
			response = Unirest.put("https://api.passslot.com/v1/passes/" + PASSLOT_API_PASS_TYPE_ID + "/" + serialNumber + "/values ")
					.header("Authorization", PASSLOT_API_AUTH)
					.header("Content-Type", PASSLOT_API_CONTENT_TYPE).body(updateJson.toString())
					.asJson();
			if(response.getStatus() == PASSLOT_API_RESPONSE_STATUS_SUCESS ){
				// Update status and save information in DB
				JSONObject updateStatusJson = new JSONObject();
				String statusKey = pass.getEstado();
				if(PassesUtil.PASSSLOT_API_STATUS_MAP.containsKey(statusKey)){
					updateStatusJson.put("status", PassesUtil.PASSSLOT_API_STATUS_MAP.get(statusKey));
					// PUT https://api.passslot.com/v1/passes/:passTypeIdentifier/:serialNumber/status
					statusResponse = Unirest.put("https://api.passslot.com/v1/passes/" + PASSLOT_API_PASS_TYPE_ID + "/" + serialNumber + "/status ")
							.header("Authorization", PASSLOT_API_AUTH)
							.header("Content-Type", PASSLOT_API_CONTENT_TYPE).body(updateStatusJson.toString())
							.asJson();
				} else {
					throw new UnirestException(new Exception("El estado indicado no esta definido"));
				}

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		PassUpdateResponse result = getPassUpdatedByResponse(response);
		return result;
	}


	private String findPassByDNI(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONArray findAllPasses() {
		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get("https://passbook.p.mashape.com/passes")
					.header("X-Mashape-Key", "tDY3sJ2lxXmsh2Ak4q1H7KkXX1ypp1aj3YsjsnSyuL2YECweOC")
					.header("Authorization", "zYdWCkbJKfOCxAgRYAwAYpcoZujHaZghUELUuhgMgUPoDMpmmZWuWmrnDZcItBWG")
					.header("Accept", "application/json").asJson();

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getBody().getArray();
	}

	@Override
	public void updatePass(Pass pass) {
		// TODO Auto-generated method stub

	}

	private PassResponse getPassByResponse(HttpResponse<JsonNode> response) {
		PassResponse result = new PassResponse();
		String serialNumber = response.getBody().getObject().getString("serialNumber");
		String passTypeIdentifier = response.getBody().getObject().getString("passTypeIdentifier");
		String url = response.getBody().getObject().getString("url");
		result.setUrl(url);
		result.setStatus(new Integer(response.getStatus()));
		result.setPassTypeIdentifier(passTypeIdentifier);
		result.setSerialNumber(serialNumber);
		return result;
	}
	
	private PassUpdateResponse getPassUpdatedByResponse(HttpResponse<JsonNode> response) {
		PassUpdateResponse result = new PassUpdateResponse();
		String estado = response.getBody().getObject().getString("estado");
		result.setEstado(estado);
		String cantClases = response.getBody().getObject().getString("cantClases");
		result.setCantClases(cantClases);
		String fechaVto = response.getBody().getObject().getString("fechaVto");
		result.setFechaVto(fechaVto);
		return result;
	}

}
