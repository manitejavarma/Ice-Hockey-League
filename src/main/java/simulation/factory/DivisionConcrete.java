package simulation.factory;

import db.dao.DivisionDao;
import db.data.IDivisionDao;
import simulation.model.Division;
import simulation.model.IDivision;

public class DivisionConcrete implements IDivisionFactory {

    public IDivision newDivision() {
        return new Division();
    }

    public IDivisionDao newDivisionDao(){
        return new DivisionDao();
    }

    public IDivision newDivisionWithIdDao(int id, IDivisionDao divisionDao) throws Exception {
        return new Division(id, divisionDao);
    }

    public IDivision newDivisionWithId(int id){
        return new Division(id);
    }
}
