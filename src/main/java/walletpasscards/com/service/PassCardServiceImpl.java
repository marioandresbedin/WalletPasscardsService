package walletpasscards.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import walletpasscards.com.model.Pass;
import walletpasscards.com.model.PassResponse;

@Service("passCardService")
@Transactional
public class PassCardServiceImpl implements PassCardService {

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
	public PassResponse createPolicyPass(Pass pass) {
		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.post("https://passbook.p.mashape.com/templates/5681803151278080/pass")
					.header("X-Mashape-Key", "tDY3sJ2lxXmsh2Ak4q1H7KkXX1ypp1aj3YsjsnSyuL2YECweOC")
					.header("Authorization", "zYdWCkbJKfOCxAgRYAwAYpcoZujHaZghUELUuhgMgUPoDMpmmZWuWmrnDZcItBWG")
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.body("{\"NameInsured\":\"" + pass.getNameInsures() + "\",\"PolicyNumber\":\"" + pass.getPolicyNumber() + "\",\"EffectiveDate\":\"" + pass.getEffectiveDate() + "\"}")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PassResponse result = getPassByResponse(response);
		return result;
	}
	
	@Override
	public PassResponse createGymPass(Pass pass) {
		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.post("https://passbook.p.mashape.com/templates/5343826923487232/pass")
					.header("X-Mashape-Key", "tDY3sJ2lxXmsh2Ak4q1H7KkXX1ypp1aj3YsjsnSyuL2YECweOC")
					.header("Authorization", "zYdWCkbJKfOCxAgRYAwAYpcoZujHaZghUELUuhgMgUPoDMpmmZWuWmrnDZcItBWG")
					.header("Content-Type", "application/json")
					.header("Accept", "application/json")
					.body("{\"nombreSocio\":\"" + pass.getNombreSocio() + "\",\"plan\":\"" + pass.getPlan() + "\",\"fechaVto\":\"" + pass.getFechaVto() + "\"}")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PassResponse result = getPassByResponse(response);
		return result;
	}

	private PassResponse getPassByResponse(HttpResponse<JsonNode> response) {
		PassResponse result = new PassResponse();
		String serialNumber = response.getBody().getObject().getString("serialNumber");
		String passTypeIdentifier = response.getBody().getObject().getString("passTypeIdentifier");
		String url = response.getBody().getObject().getString("url");		
		result.setUrl(url);
		result.setPassTypeIdentifier(passTypeIdentifier);
		result.setSerialNumber(serialNumber);
		return result;
	}

	@Override
	public List<Pass> findAllPasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePass(Pass pass) {
		// TODO Auto-generated method stub
		
	}

}
