package simulation.state;

import presentation.ConsoleOutput;
import simulation.model.DateTime;
import simulation.model.League;
import simulation.model.NHLEvents;

import java.time.LocalDate;

public class AdvanceTimeState implements ISimulateState {

    private League league;
    private HockeyContext hockeyContext;

    public AdvanceTimeState(HockeyContext hockeyContext) {
        this.hockeyContext = hockeyContext;
        this.league = hockeyContext.getUser().getLeague();
    }

    @Override
    public ISimulateState action() {
        LocalDate advancedDate = DateTime.addDays(league.getCurrentDate(), 1);
        league.setCurrentDate(advancedDate);
        ConsoleOutput.getInstance().printMsgToConsole("Advanced Day. Current date is " + advancedDate);
        return exit();
    }

    private ISimulateState exit() {
        NHLEvents nhlEvents = league.getNHLRegularSeasonEvents();
        if (nhlEvents.checkEndOfRegularSeason(league.getCurrentDate()) || nhlEvents.checkRegularSeasonPassed(league.getCurrentDate())) {
            return new GeneratePlayoffScheduleState(hockeyContext);
        } else {
            return new TrainingState(hockeyContext);
        }
    }
}