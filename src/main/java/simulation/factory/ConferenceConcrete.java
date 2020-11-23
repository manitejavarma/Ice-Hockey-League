package simulation.factory;

import db.dao.ConferenceDao;
import db.data.IConferenceDao;
import simulation.model.Conference;
import simulation.model.IConference;

public class ConferenceConcrete implements IConferenceFactory{

    public IConference newConference() {
        return new Conference();
    }

    public IConferenceDao newConferenceDao(){
        return new ConferenceDao();
    }

    public IConference newConferenceWithId(int id){
        return new Conference(id);
    }

    public IConference newConferenceWithIdDao(int id, IConferenceDao conferenceDao) throws Exception {
        return new Conference(id, conferenceDao);
    }

}
