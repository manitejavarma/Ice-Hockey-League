package simulation.state;
import simulation.RegularSeasonEvents.NHLEvents;
import simulation.model.*;
import userIO.ConsoleOutput;

import java.util.List;
import java.util.Random;

public class TrainingState implements ISimulateState {

    private HockeyContext hockeyContext;
    private League league;

    public TrainingState(HockeyContext hockeyContext) {
        this.hockeyContext = hockeyContext;
        league = hockeyContext.getUser().getLeague();
    }

    @Override
    public ISimulateState action() {
        ConsoleOutput.printToConsole("Training Players and Team!");
        return exit();
    }

    private void StatIncreaseCheck(League league) {
        List<Conference> conferenceList=league.getConferenceList();
        for(Conference conference : conferenceList){
            List<Division> divisionList = conference.getDivisionList();
            for(Division division : divisionList){
                List<Team> teamList = division.getTeamList();
                for(Team team : teamList){
                    List<Player> playerList = team.getPlayerList();
                    for(Player player : playerList){
                        StatIncreaseCheckPlayer(player, team.getCoach());
                    }
                }
            }
        }
    }

    private void StatIncreaseCheckPlayer(Player player, Coach headCoach){

        double coachStrengthShooting=headCoach.getShooting();
        double coachStrengthSkating = headCoach.getSkating();
        double coachStrengthChecking = headCoach.getChecking();
        double coachStrengthSaving = headCoach.getSaving();

        if(isRandomLess(coachStrengthShooting)){
            player.setShooting(player.getShooting()+1);
        }else{
            // run injury check
        }
        if(isRandomLess(coachStrengthSkating)){
            player.setSkating(player.getSkating()+1);
        }else{
            // run injury check
        }
        if(isRandomLess(coachStrengthChecking)){
            player.setChecking(player.getChecking()+1);
        }else{
            // run injury check
        }
        if(isRandomLess(coachStrengthSaving)){
            player.setSaving(player.getSaving()+1);
        }else{
            // run injury check
        }
    }

    boolean isRandomLess(double coachStrength){
        Random rand = new Random();
        double randomNumber = rand.nextDouble();
        if(randomNumber < coachStrength){
            return true;
        }else{
            return false;
        }
    }

    private ISimulateState exit() {
        NHLEvents nhlEvents = league.getNhlRegularSeasonEvents();

        Games games = league.getGames();
        List<Game> gamesOnCurrentDay = games.getGamesOnDate(league.getCurrentDate());
        if(gamesOnCurrentDay.size()!=0){
            return new SimulateGameState(hockeyContext);
        }else{
            if(nhlEvents.isTradeDeadlinePassed(league.getCurrentDate())){
                return new AgingState(hockeyContext);
            }else{
                return new ExecuteTradeState(hockeyContext);
            }
        }
    }
}
