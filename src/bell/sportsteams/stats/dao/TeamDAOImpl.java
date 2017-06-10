package bell.sportsteams.stats.dao;

import java.net.MalformedURLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bell.sportsteams.sportsreferenceparser.WebPageParser;
import bell.sportsteams.stats.entity.Team;

/**
 * @author Michael Bell
 *implementation of the data access object for team database
 */
@Repository
public class TeamDAOImpl implements TeamDAO {

	//inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Team> getTeams() {
		
		//get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//query the database
		Query<Team> theQuery = currentSession.createQuery("from Team", Team.class);
	
		//execute the query and return the results
		return theQuery.getResultList();
	}

	@Override
	public void saveTeam(Team theTeam) {
		//get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save the team, if already present will update the file
		currentSession.saveOrUpdate(theTeam);
		
		//call method to update stats, in order to pre-populate table
		this.refreshTeam(theTeam.getId());
	}

	@Override
	public void deleteTeam(int theId) {
		
		//get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete the team using theId (primary key)
		Query theQuery = currentSession.createQuery("delete from Team where id=:teamId");
		theQuery.setParameter("teamId", theId);
		
		//execute the remove
		theQuery.executeUpdate();
		
	}
	
	
	//Will refresh all stats at once for a single team
	@Override
	public void refreshTeam(int theId) {
	
		//Initialize variables
		int wins = 0;
		int losses = 0;
		float fgPct = 0;
		
		//get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//query to get school name from theId
		Query theQuery = currentSession.createQuery("select schoolName from Team where id=:teamId");
		theQuery.setParameter("teamId", theId);
		
		//retrieve school name from theQuery
		String schoolName = (String) theQuery.getSingleResult();

		//Create the object that will parse our statistics
		WebPageParser webParser;
		
		//try-catch to get statistics from web
		try {
			webParser = new WebPageParser();
		
			//Parse the required statistics from the internet
			wins = webParser.getSchoolWins(schoolName);
			losses = webParser.getSchoolLosses(schoolName);
			fgPct = (float) webParser.getSearchStart(schoolName, "fg_pct");
			
		} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		//create the query for updating win column
		Query updateQuery = currentSession.createQuery("update Team set wins=:winSet" 
				+ " where id=:teamId");
		updateQuery.setParameter("winSet", wins);
		updateQuery.setParameter("teamId", theId);
		
		//execute change for updating wins
		updateQuery.executeUpdate();
		
		//create the query for updating fg_pct column
		updateQuery = currentSession.createQuery("update Team set fg_pct=:fgPct" 
				+ " where id=:teamId");
		updateQuery.setParameter("fgPct", fgPct);
		updateQuery.setParameter("teamId", theId);
		
		//execute change for updating fg_pct
		updateQuery.executeUpdate();
		
		//create the query for updating losses column
		updateQuery = currentSession.createQuery("update Team set losses=:lossesSet" 
				+ " where id=:teamId");
		updateQuery.setParameter("lossesSet", losses);
		updateQuery.setParameter("teamId", theId);
		
		//execute our change for losses
		updateQuery.executeUpdate();
		
	}

}
