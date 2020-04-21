package dk.dtu.gbar.gitlab.shipment;

public class LogisticCompanyUser extends ClientUser {


//Constructor 
	public LogisticCompanyUser(LogisticCompany logisticCompany) {
		super(logisticCompany);
	}

	public final void informEmbarkation(Ship ship) {
		ship.embark();
	}

}
