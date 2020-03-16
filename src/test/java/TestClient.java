import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestClient {
    DataBase Catalog;
    ArrayList<Client> ClientList;
    Client BananaMan;
    Client TheseNuts;
    Client NutsRUs;
    @Before
    public void setUp(){
        Catalog = new DataBase();
        BananaMan = new Client("Bananaman", "Banana drive 123", "B. Ananas", "banana.man@Bananaman.com");
        TheseNuts = new Client("Thesenuts", "Nutting alley 456", "N. U. Tting", "these.nuts@nutcity.com");
        NutsRUs = new Client("NutsRUs", "Nuttingham 420", "N. U. Tting", "nutsrus@nutcity.com");
        Catalog.ClientList = new ArrayList<Client>();
        Catalog.ClientList.add(BananaMan);
        Catalog.ClientList.add(TheseNuts);
        Catalog.ClientList.add(NutsRUs);
    }

    @Test
    public void testGetInfo(){
        assertEquals("Bananaman", BananaMan.getName());
        assertEquals("Banana drive 123", BananaMan.getAddress());
        assertEquals("B. Ananas", BananaMan.getRefPerson());
        assertEquals("banana.man@Bananaman.com", BananaMan.getEmail());
    }


    @Test
    public void testUpdateInfo(){
        BananaMan.setName("Pineapple boi");
        BananaMan.setAddress("Orange street 456");
        BananaMan.setRefPerson("Spongebob");
        BananaMan.setEmail("Bigboi@Bananaman.com");
        assertEquals("Pineapple boi", BananaMan.getName());
        assertEquals(BananaMan.getAddress(), "Orange street 456");
        assertEquals(BananaMan.getRefPerson(), "Spongebob");
        assertEquals(BananaMan.getEmail(), "Bigboi@Bananaman.com");
    }

    @Test
    public void testFindClient(){
        assertEquals(Catalog.searchClient("N. U. Tting"), new ArrayList<Client>(Arrays.asList(new Client[]{TheseNuts, NutsRUs})));
    }

}
