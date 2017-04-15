package walletpasscards.com.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import walletpasscards.com.model.Pass;

public class PassesUtil {

	/*
	 * Constants to use in Passlot API
	 */
	public static String _PASS_TYPE_ID = "";
	public static String _PASS_TEMPLATE_GYM_ID = "";
	public static String _PASS_TEMPLATE_INSURANCE_ID = "";
	
	public static String _PASS_STATUS_VALID = "valid";
	public static String _PASS_STATUS_INVALID = "invalid";
	public static String _PASS_STATUS_UNPAID = "unpaid";
	public static String _PASS_STATUS_LOCKED = "locked";
	public static String _PASS_STATUS_REDEEMED = "redeemed";

	
	public static Map<String, String> PASSSLOT_API_STATUS_MAP = new HashMap<String, String>();
	
	static {
		PASSSLOT_API_STATUS_MAP.put("debe cuota", _PASS_STATUS_UNPAID);
		PASSSLOT_API_STATUS_MAP.put("Al dia", _PASS_STATUS_VALID);
	}

	/*
	 * Method to retrieve JSON update values to send to PASSSLOT API
	 */
	public static JSONObject getJsonFromPassCard(Pass pass) {
		JSONObject json = new JSONObject();
		if(pass.getClases()!= null && !pass.getClases().isEmpty()){
			json.put("clases", pass.getClases());    
		}
		if(pass.getEstado()!= null && !pass.getEstado().isEmpty()){
			json.put("estado", pass.getEstado());    
		}
		if(pass.getDni()!= null && !pass.getDni().isEmpty()){
			json.put("dni", pass.getDni());    
		}
		if(pass.getEmail()!= null && !pass.getEmail().isEmpty()){
			json.put("email", pass.getEmail());    
		}
		if(pass.getFechaVto()!= null && !pass.getFechaVto().isEmpty()){
			json.put("fechaVto", pass.getFechaVto());    
		}
		if(pass.getNroSocio()!= null && !pass.getNroSocio().isEmpty()){
			json.put("nroSocio", pass.getNroSocio());    
		}
		if(pass.getNombreSocio()!= null && !pass.getNombreSocio().isEmpty()){
			json.put("nombreSocio", pass.getNombreSocio());    
		}
		if(pass.getCantClases()!= null && !pass.getCantClases().isEmpty()){
			json.put("cantClases", pass.getCantClases());    
		}
		return json;
	}

}
