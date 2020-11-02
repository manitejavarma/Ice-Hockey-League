package simulation.model;

import db.data.IInjuryFactory;

public class Injury extends SharedAttributes {

    private Double randomInjuryChance;

    private int injuryDaysLow;

    private int injuryDaysHigh;

    private int leagueId;

    public Injury() {
    }

    public Injury(int id) {
        setId(id);
    }

    public Injury(int id, IInjuryFactory loadInjuryFactory) throws Exception {
        setId(id);
        loadInjuryFactory.loadInjuryById(id, this);
    }

    public Double getRandomInjuryChance() {
        return randomInjuryChance;
    }

    public void setRandomInjuryChance(Double randomInjuryChance) {
        if (randomInjuryChance < 0 || randomInjuryChance > 1) {
            throw new IllegalArgumentException("randomInjuryChance must be between 0 and 1");
        }
        this.randomInjuryChance = randomInjuryChance;
    }

    public int getInjuryDaysLow() {
        return injuryDaysLow;
    }

    public void setInjuryDaysLow(int injuryDaysLow) {
        if (injuryDaysLow < 0) {
            throw new IllegalArgumentException("injuryDaysLow must be greater than 0!");
        }
        this.injuryDaysLow = injuryDaysLow;
    }

    public int getInjuryDaysHigh() {
        return injuryDaysHigh;
    }

    public void setInjuryDaysHigh(int injuryDaysHigh) {
        if (injuryDaysHigh < 0) {
            throw new IllegalArgumentException("injuryDaysHigh must be greater than 0!");
        }
        if (this.getInjuryDaysLow() > injuryDaysHigh) {
            throw new IllegalArgumentException("InjuryDaysHigh age must be greater than InjuryDaysLow!");
        }
        this.injuryDaysHigh = injuryDaysHigh;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

}
