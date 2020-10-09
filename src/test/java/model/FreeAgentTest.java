package model;

import data.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FreeAgentTest {

    private static ILoadFreeAgentFactory loadFreeAgentFactory;
    private static IAddFreeAgentFactory addFreeAgentFactory;

    @BeforeClass
    public static void setFactoryObj(){
        addFreeAgentFactory = new AddFreeAgentMock();
        loadFreeAgentFactory = new LoadFreeAgentMock();
    }

    @Test
    public void defaultConstructorTest() {
        FreeAgent freeAgent = new FreeAgent();
        assertEquals(freeAgent.getId(), 0);
    }

    @Test
    public void freeAgentTest() {
        FreeAgent freeAgent = new FreeAgent(1);
        assertEquals(freeAgent.getId(), 1);
    }

    @Test
    public void freeAgentFactoryTest() throws Exception {
        FreeAgent freeAgent = new FreeAgent(1, loadFreeAgentFactory);
        List<Player> playerList = freeAgent.getPlayerList();
        Player player = playerList.get(0);
        assertEquals(player.getId(),1);
        assertTrue(player.getName().equals("Player1"));
    }

    @Test
    public void getPlayerListTest() throws Exception {
        FreeAgent freeAgent = new FreeAgent(1, loadFreeAgentFactory);
        List<Player> playerList = freeAgent.getPlayerList();
        assertNotNull(playerList);
        assertTrue(playerList.get(0).getId() == (1));
        assertTrue(playerList.get(1).getId() == (5));
        assertTrue(playerList.get(0).getName().equals("Player1"));
        assertTrue(playerList.get(1).getName().equals("Player5"));
    }

    @Test
    public void setPlayerListTest() throws Exception {
        ILoadPlayerFactory playerFactory = new LoadPlayerMock();
        List<Player> playerList = new ArrayList<>();
        Player player = new Player(1, playerFactory);
        playerList.add(player);
        player = new Player(5, playerFactory);
        playerList.add(player);

        FreeAgent freeAgent = new FreeAgent();
        freeAgent.setPlayerList(playerList);

        assertTrue(freeAgent.getPlayerList().get(0).getId() == (1));
        assertTrue(freeAgent.getPlayerList().get(1).getId() == (5));
        assertTrue(freeAgent.getPlayerList().get(0).getName().equals("Player1"));
        assertTrue(freeAgent.getPlayerList().get(1).getName().equals("Player5"));
    }

    @Test
    public void addFreeAgentTest() throws Exception {
        FreeAgent freeAgent = new FreeAgent();
        freeAgent.setId(1);
        freeAgent.setName("FreeAgent1");
        freeAgent.addFreeAgent(addFreeAgentFactory);
        assertTrue(1 == freeAgent.getId());
        assertTrue("FreeAgent1".equals(freeAgent.getName()));
    }

    @Test
    public void getSeasonIdTest() throws Exception {
        FreeAgent freeAgent = new FreeAgent(1, loadFreeAgentFactory);
        assertTrue(freeAgent.getSeasonId() == (1));
    }

    @Test
    public void setSeasonIdTest(){
        FreeAgent freeAgent = new FreeAgent();
        int seasonId = 1;
        freeAgent.setSeasonId(seasonId);
        assertTrue(freeAgent.getSeasonId() == seasonId);
    }

    @Test
    public void getLeagueIdTest() throws Exception {
        FreeAgent freeAgent = new FreeAgent(1, loadFreeAgentFactory);
        assertTrue(freeAgent.getLeagueId() == (1));
    }

    @Test
    public void setLeagueIdTest(){
        FreeAgent freeAgent = new FreeAgent();
        int leagueId = 1;
        freeAgent.setLeagueId(leagueId);
        assertTrue(freeAgent.getLeagueId() == leagueId);
    }

    @Test
    public void loadPlayerListByFreeAgentId() throws Exception {
        FreeAgent freeAgent = new FreeAgent(1);
        ILoadPlayerFactory playerFactory = new LoadPlayerMock();
        freeAgent.loadPlayerListByFreeAgentId(playerFactory);

        assertTrue(freeAgent.getPlayerList().get(0).getId() == (1));
        assertTrue(freeAgent.getPlayerList().get(1).getId() == (5));
        assertTrue(freeAgent.getPlayerList().get(0).getName().equals("Player1"));
        assertTrue(freeAgent.getPlayerList().get(1).getName().equals("Player5"));
    }


}