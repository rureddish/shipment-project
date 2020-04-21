package dk.dtu.gbar.gitlab.shipment;

public class LogisticCompanyUser extends ClientUser {


//Constructor 
	public LogisticCompanyUser(LogisticCompany logisticCompany) {
		super(logisticCompany);
	}

	@SafeVarargs
	public final void informEmbarkation(Ship ship, int... IDs) {
		ship.embark(((LogisticCompany)client).getSearchContainer(), ((LogisticCompany) client).getContainerList(), IDs);		
	}

}
