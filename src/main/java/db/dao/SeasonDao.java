package db.dao;

import db.data.ISeasonFactory;
import simulation.model.Season;

import java.sql.SQLException;

public class SeasonDao extends DBExceptionLog implements ISeasonFactory {
    @Override
    public int addSeason(Season season) throws Exception {
        String addSeasonProcedureName = "AddSeason(?,?)";
        ICallDB callDB = null;
        try {
            callDB = new CallDB(addSeasonProcedureName);
            callDB.setInputParameterString(1, season.getName());
            callDB.setOutputParameterInt(2);
            callDB.execute();
            season.setId(callDB.returnOutputParameterInt(2));

        } catch (SQLException sqlException) {
            printLog("SeasonDao: addSeason: SQLException: " + sqlException);
            throw sqlException;
        } finally {
            if (getValidation().isNotNull(callDB)) {
                callDB.closeConnection();
            }
        }
        return season.getId();
    }

    @Override
    public void loadSeasonById(int id, Season season) throws Exception {
        String loadSeasonByNameProcedureName = "LoadSeasonByName(?,?,?)";
        ICallDB callDB = null;
        try {
            callDB = new CallDB(loadSeasonByNameProcedureName);
            callDB.setInputParameterInt(1, id);
            callDB.setOutputParameterInt(2);
            callDB.setOutputParameterString(3);
            callDB.executeLoad();

            season.setId(callDB.returnOutputParameterInt(2));
            season.setName(callDB.returnOutputParameterString(3));
        } catch (SQLException sqlException) {
            printLog("SeasonDao: loadSeasonById: SQLException: " + sqlException);
            throw sqlException;
        } finally {
            if (getValidation().isNotNull(callDB)) {
                callDB.closeConnection();
            }
        }
    }
}