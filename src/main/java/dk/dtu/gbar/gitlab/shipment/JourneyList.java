package dk.dtu.gbar.gitlab.shipment;

public class JourneyList extends EntityList<Journey> {

    public JourneyList() {
        super();
    }

    @Override
    public void add(Journey journey) {
        if (journeyOriginHasContainers(journey)) {
            super.add(journey);
            journey.setContainer(journey.getOrigin().getLocationContainers().remove());
            journey.getContainer().getJourneyHistory().add(journey);
        } else {
            System.out.println("no containers in port");
        }
    }

    public boolean journeyOriginHasContainers(Journey object) {
        return 0 < object.getOrigin().getLocationContainers().size();
    }
}
