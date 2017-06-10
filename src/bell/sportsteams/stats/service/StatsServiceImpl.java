package bell.sportsteams.stats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bell.sportsteams.stats.dao.TeamDAO;
import bell.sportsteams.stats.entity.Team;

/**
 * @author Michael Bell
 * implementation of the service layer for the team database
 */
@Service
public class StatsServiceImpl implements StatsService {

	//inject the team DAO
	@Autowired
	private TeamDAO teamDAO;
	
	//start & end the transaction called from TeamDAO
	@Override
	@Transactional
	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}

	@Override
	@Transactional
	public void saveTeam(Team theTeam) {
		teamDAO.saveTeam(theTeam);
		
	}

	@Override
	@Transactional
	public void deleteTeam(int theId) {
		teamDAO.deleteTeam(theId);
	}
	
	@Override
	@Transactional
	public void refreshTeam(int theId) {
		teamDAO.refreshTeam(theId);
	}

}
