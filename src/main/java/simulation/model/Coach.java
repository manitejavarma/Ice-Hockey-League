package simulation.model;

import db.data.ICoachFactory;

public class Coach extends SharedAttributes {
    private int teamId;
    private int leagueId;
    private Double skating;
    private Double shooting;
    private Double checking;
    private Double saving;

    public Coach() {
    }

    public Coach(int id) {
        setId(id);
    }

    public Coach(Coach coach) {
        if (coach == null) {
            return;
        }
        this.setId(coach.getId());
        if (isNotNull(coach.getName())) {
            this.setName(coach.getName());
        }
        this.setTeamId(coach.getTeamId());
        this.setChecking(coach.getChecking());
        this.setLeagueId(coach.getLeagueId());
        this.setSaving(coach.getSaving());
        this.setShooting(coach.getShooting());
        this.setSkating(coach.getSkating());
    }

    public Coach(int id, ICoachFactory coachFactory) throws Exception {
        if (coachFactory == null) {
            return;
        }
        setId(id);
        coachFactory.loadCoachById(id, this);
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }


    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public Double getSkating() {
        return skating;
    }

    public void setSkating(Double skating) {
        if (skating == null) {
            return;
        }
        if (skating < 0 || skating > 1) {
            throw new IllegalArgumentException("Coach statistics must between 0 and 1!");
        }
        this.skating = skating;
    }

    public Double getShooting() {
        return shooting;
    }

    public void setShooting(Double shooting) {
        if (shooting == null) {
            return;
        }
        if (shooting < 0 || shooting > 1) {
            throw new IllegalArgumentException("Coach statistics must between 0 and 1!");
        }
        this.shooting = shooting;
    }

    public Double getChecking() {
        return checking;
    }

    public void setChecking(Double checking) {
        if (checking == null) {
            return;
        }
        if (checking < 0 || checking > 1) {
            throw new IllegalArgumentException("Coach statistics must between 0 and 1!");
        }
        this.checking = checking;
    }

    public Double getSaving() {
        return saving;
    }

    public void setSaving(Double saving) {
        if (saving == null) {
            return;
        }
        if (saving < 0 || saving > 1) {
            throw new IllegalArgumentException("Coach statistics must between 0 and 1!");
        }
        this.saving = saving;
    }


}