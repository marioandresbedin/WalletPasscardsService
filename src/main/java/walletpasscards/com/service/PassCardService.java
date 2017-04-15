package walletpasscards.com.service;

import org.json.JSONArray;

import walletpasscards.com.model.Pass;
import walletpasscards.com.model.PassResponse;
import walletpasscards.com.model.PassUpdateResponse;



public interface PassCardService {

	Pass findById(long id);

	Pass findByName(String name);
	
	PassResponse createGymPass(Pass pass);
	
	PassUpdateResponse updateGymPass(Pass pass);

	JSONArray findAllPasses();
	
	void updatePass(Pass pass);
	

}
