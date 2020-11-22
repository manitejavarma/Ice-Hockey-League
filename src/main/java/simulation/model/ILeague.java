package simulation.model;

import db.data.IConferenceDao;
import db.data.IFreeAgentDao;
import db.data.ILeagueDao;
import db.data.ITradeOfferDao;
import simulation.factory.ICoachFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ILeague{

    INHLEvents getNHLRegularSeasonEvents();

    void setNhlRegularSeasonEvents(INHLEvents nhlEvents);

    IGameSchedule getGames();

    void setGames(IGameSchedule games);

    IGamePlayConfig getGamePlayConfig();

    void setGamePlayConfig(IGamePlayConfig gamePlayConfig);

    LocalDate getCurrentDate();

    void setCurrentDate(LocalDate currentDate);

    List<IConference> getConferenceList();

    void setConferenceList(List<IConference> conferenceList);

    List<ICoach> getCoachList();

    void setCoachList(List<ICoach> coachList);

    List<IManager> getManagerList();

    void setManagerList(List<IManager> managerList);

    List<IPlayer> getRetiredPlayerList();

    void setRetiredPlayerList(List<IPlayer> retiredPlayerList);

    ITeamStanding getRegularSeasonStanding();

    void setRegularSeasonStanding(ITeamStanding regularSeasonStanding);

    ITeamStanding getPlayOffStanding();

    void setPlayOffStanding(ITeamStanding playOffStanding);

    ITeamStanding getActiveTeamStanding();

    void setActiveTeamStanding(ITeamStanding activeTeamStanding);

    HashMap<String,Integer> getStanleyCupFinalsTeamScores();

    void setStanleyCupFinalsTeamScores(HashMap<String, Integer> stanleyCupFinalsTeamScores);

    List<IManager> removeManagerFromManagerListById(List<IManager> managerList, int indexOfManagerObject);

    List<ICoach> removeCoachFromCoachListById(List<ICoach> coachList,
                                                    int indexOfCoachObject, ICoachFactory coachFactory);

    int getCreatedBy();

    void setCreatedBy(int createdBy);

    IFreeAgent getFreeAgent();

    void setFreeAgent(IFreeAgent freeAgent);

    void addLeague(ILeagueDao addLeagueFactory) throws Exception;

    void loadConferenceListByLeagueId(IConferenceDao loadConferenceFactory) throws Exception;

    List<String> createConferenceNameList();

    IConference getConferenceFromListByName(String conferenceName);

    void loadFreeAgentByLeagueId(IFreeAgentDao loadFreeAgentFactory) throws Exception;

    ITeam getTeamByTeamName(String teamName);

    List<ITradeOffer> getTradeOfferList();

    void setTradeOfferList(List<ITradeOffer> tradeOfferList);

    void loadTradingOfferDetailsByLeagueId(ITradeOfferDao tradingOfferFactory) throws Exception;

    int getId();

    String getName();

    void setName(String name);

    String getUserCreatedTeamName();

    void setUserCreatedTeamName(String userCreatedTeamName);

    String getUser();

    void setUser(String user);

}
