package walletpasscards.com.model;

public class PassResponse {

	private String serialNumber;
	private String passTypeIdentifier;
	private String url;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPassTypeIdentifier() {
		return passTypeIdentifier;
	}

	public void setPassTypeIdentifier(String passTypeIdentifier) {
		this.passTypeIdentifier = passTypeIdentifier;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
