import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    Database catalog;
    Client bananaMan;
    Client theseNuts;
    Client nutsRUs;

    @Before
    public void setUp() {
        catalog = new Database();
        bananaMan = new Client("Bananaman", "Banana drive 123", "B. Ananas", "banana.man@Bananaman.com");
        theseNuts = new Client("Thesenuts", "Nutting alley 456", "N. U. Tting", "these.nuts@nutcity.com");
        nutsRUs = new Client("NutsRUs", "Nuttingham 420", "N. U. Tting", "nutsrus@nutcity.com");
        catalog.addClient(bananaMan);
        catalog.addClient(theseNuts);
        catalog.addClient(nutsRUs);
    }

    @Test
    public void testGetInfo() {
        assertEquals("Bananaman", bananaMan.getName());
        assertEquals("Banana drive 123", bananaMan.getAddress());
        assertEquals("B. Ananas", bananaMan.getRefPerson());
        assertEquals("banana.man@Bananaman.com", bananaMan.getEmail());
    }


    @Test
    public void testUpdateInfo() {
        bananaMan.setName("Pineapple boi");
        bananaMan.setAddress("Orange street 456");
        bananaMan.setRefPerson("Spongebob");
        bananaMan.setEmail("Bigboi@Bananaman.com");
        assertEquals("Pineapple boi", bananaMan.getName());
        assertEquals(bananaMan.getAddress(), "Orange street 456");
        assertEquals(bananaMan.getRefPerson(), "Spongebob");
        assertEquals(bananaMan.getEmail(), "Bigboi@Bananaman.com");
    }

    @Test
    public void testFindClient() {
        assertEquals(catalog.searchClient("N. U. Tting"), new ArrayList<>(Arrays.asList(theseNuts, nutsRUs)));
    }
    @Test
    public void testFindNonExistingClient(){
        assertEquals(catalog.searchClient("Not nutting"), new ArrayList<Client>());
    }

}
