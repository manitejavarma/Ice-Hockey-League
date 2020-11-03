package db.dao;

import db.data.IGameFactory;
import simulation.model.DateTime;
import simulation.model.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameDao extends DBExceptionLog implements IGameFactory {
    @Override
    public long addGame(int leagueId, Game game) throws Exception {
        ICallDB callDB = null;
        try {
            String procedureName = "AddGame(?,?,?,?,?,?,?)";
            callDB = new CallDB(procedureName);
            callDB.setInputParameterInt(1, leagueId);
            callDB.setInputParameterDate(2, DateTime.convertLocalDateToSQLDate(game.getDate()));
            callDB.setInputParameterString(3, game.getTeam1());
            callDB.setInputParameterString(4, game.getTeam2());
            callDB.setInputParameterBoolean(5, game.getPlayed());
            if (game.getWinner() == null) {
                callDB.setInputParameterString(6, null);
            } else {
                callDB.setInputParameterString(6, game.getWinner().toString());
            }

            callDB.setOutputParameterInt(7);
            callDB.execute();
            game.setId(callDB.returnOutputParameterInt(7));

        } catch (SQLException sqlException) {
            printLog("GameDao: addGame: SQLException: " + sqlException);
            throw sqlException;
        } finally {
            if (getValidation().isNotNull(callDB)) {
                callDB.closeConnection();
            }
        }
        return game.getId();
    }

    @Override
    public void loadGameById(int id, Game game) throws Exception {
        ICallDB callDB = null;
        try {
            String procedureName = "LoadGameById(?)";
            callDB = new CallDB(procedureName);
            callDB.setInputParameterInt(1, id);
            ResultSet rs = callDB.executeLoad();
            if (getValidation().isNotNull(rs)) {
                game.setId(rs.getInt(1));
                game.setDate(rs.getDate(2).toLocalDate());
                game.setTeam1(rs.getString(3));
                game.setTeam2(rs.getString(4));
                game.setPlayed(rs.getBoolean(5));
                game.setWinner(Game.Result.TEAM1);
            }
        } catch (SQLException sqlException) {
            printLog("GameDao: loadGameById: SQLException: " + sqlException);
            throw sqlException;
        } finally {
            if (getValidation().isNotNull(callDB)) {
                callDB.closeConnection();
            }
        }
    }

    @Override
    public List<Game> loadGamesByLeagueId(int leagueId) throws Exception {
        List<Game> gameList = null;
        ICallDB callDB = null;
        try {
            String procedureName = "LoadGameByLeagueId(?)";
            callDB = new CallDB(procedureName);
            callDB.setInputParameterInt(1, leagueId);
            ResultSet rs = callDB.executeLoad();
            if (getValidation().isNotNull(rs)) {
                gameList = new ArrayList<>();
                while (rs.next()) {
                    Game game = new Game();
                    game.setId(rs.getInt(1));
                    game.setDate(rs.getDate(2).toLocalDate());
                    game.setTeam1(rs.getString(3));
                    game.setTeam2(rs.getString(4));
                    game.setPlayed(rs.getBoolean(5));
                    game.setWinner(Game.fromString(rs.getString(6)));
                    gameList.add(game);
                }
            }
        } catch (SQLException sqlException) {
            printLog("GameDao: loadGamesByLeagueId: SQLException: " + sqlException);
            throw sqlException;
        } finally {
            if (getValidation().isNotNull(callDB)) {
                callDB.closeConnection();
            }
        }
        return gameList;
    }

    @Override
    public void updateGameById(Game game) throws Exception {
        ICallDB callDB = null;
        try {
            String procedureName = "UpdateGameById(?,?,?,?,?,?)";
            callDB = new CallDB(procedureName);
            callDB.setInputParameterDate(1, DateTime.convertLocalDateToSQLDate(game.getDate()));
            callDB.setInputParameterString(2, game.getTeam1());
            callDB.setInputParameterString(3, game.getTeam2());
            callDB.setInputParameterBoolean(4, game.getPlayed());
            if (game.getWinner() == null) {
                callDB.setInputParameterString(5, null);
            } else {
                callDB.setInputParameterString(5, game.getWinner().toString());
            }
            callDB.setInputParameterInt(6, game.getId());
            callDB.execute();

        } catch (SQLException sqlException) {
            printLog("GameDao: addGame: SQLException: " + sqlException);
            throw sqlException;
        } finally {
            if (getValidation().isNotNull(callDB)) {
                callDB.closeConnection();
            }
        }
    }
}