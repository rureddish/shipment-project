package dk.dtu.gbar.gitlab.shipment;

public class LogisticsCompanyUser extends ClientUser {


//Constructor 
	public LogisticsCompanyUser(LogisticsCompany logisticCompany) {
		super(logisticCompany);
	}

	protected final void departShip(Ship ship) {
		ship.embark();
	}

}
