package simulation.state;

import simulation.factory.ValidationConcrete;
import simulation.model.Game;
import simulation.model.League;
import simulation.model.Team;
import simulation.model.TeamStanding;
import validator.IValidation;

import java.util.List;

public class SimulateGameState implements ISimulateState {

    private HockeyContext hockeyContext;
    private League league;
    private IValidation validation;

    public SimulateGameState(HockeyContext hockeyContext) {
        this.hockeyContext = hockeyContext;
        league = hockeyContext.getUser().getLeague();
        ValidationConcrete validationConcrete = new ValidationConcrete();
        validation = validationConcrete.newValidation();
    }

    @Override
    public ISimulateState action() {
        List<Game> gamesOnCurrentDay = league.getGames().getUnPlayedGamesOnDate(league.getCurrentDate());
        Game game = gamesOnCurrentDay.get(0);

        simulateGame(game);
        updateTeamStandings(game);

        return exit();
    }

    public ISimulateState exit() {
        return new InjuryCheckState(hockeyContext);
    }

    public void simulateGame(Game game) {
        double upset = league.getGamePlayConfig().getGameResolver().getRandomWinChance();
        Team team1 = league.getTeamByTeamName(game.getTeam1());
        Team team2 = league.getTeamByTeamName(game.getTeam2());

        if (validation.isNotNull(team1) && validation.isNotNull(team2)) {
            if (team1.getStrength() > team2.getStrength()) {
                game.setWinner(Game.Result.TEAM1);
                team2.setLossPoint(team2.getLossPoint() + 1);
            } else {
                game.setWinner(Game.Result.TEAM2);
                team1.setLossPoint(team1.getLossPoint() + 1);
            }
            if (Math.random() <= upset) {
                if (game.getWinner().equals(Game.Result.TEAM1)) {
                    game.setWinner(Game.Result.TEAM2);
                    team1.setLossPoint(team1.getLossPoint() + 1);
                } else {
                    game.setWinner(Game.Result.TEAM1);
                    team2.setLossPoint(team2.getLossPoint() + 1);
                }
            }
            game.setPlayed(true);
        }
    }

    public void updateTeamStandings(Game game) {
        TeamStanding teamStanding = league.getActiveTeamStanding();

        if (game.getWinner().equals(Game.Result.TEAM1)) {
            teamStanding.setTeamPoints(game.getTeam1());
            teamStanding.setTeamWins(game.getTeam1());
            teamStanding.setTeamLoss(game.getTeam2());
        } else if (game.getWinner().equals(Game.Result.TEAM2)) {
            teamStanding.setTeamPoints(game.getTeam2());
            teamStanding.setTeamWins(game.getTeam2());
            teamStanding.setTeamLoss(game.getTeam1());
        } else {
            teamStanding.setTeamTies(game.getTeam2());
            teamStanding.setTeamTies(game.getTeam1());
        }
    }
}