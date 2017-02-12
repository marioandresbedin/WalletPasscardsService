package walletpasscards.com.service;

import java.util.List;

import walletpasscards.com.model.Pass;
import walletpasscards.com.model.PassResponse;

public interface PassCardService {

	Pass findById(long id);

	Pass findByName(String name);

	PassResponse createPolicyPass(Pass pass);
	
	PassResponse createGymPass(Pass pass);

	List<Pass> findAllPasses();
	
	void updatePass(Pass pass);


}
