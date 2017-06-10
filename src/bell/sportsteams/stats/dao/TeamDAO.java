package bell.sportsteams.stats.dao;

import java.util.List;

import bell.sportsteams.stats.entity.Team;

/**
 * @author Michael Bell
 *Data access object for team database
 */
public interface TeamDAO {
	
	/**
	 * Returns all teams in database
	 * @return List<Team>
	 */
	public List<Team> getTeams(); //returns all teams in database

	/**
	 * Will save associated team and all team statistics to database
	 * @param theTeam school to be saved
	 */
	public void saveTeam(Team theTeam);

	/**
	 * remove team from database
	 * @param theId primary id of team to be deleted
	 */
	public void deleteTeam(int theId);

	/**
	 * Parses the given webpage for updated statistics
	 *@param theId primary id of team to be updated
	 */
	public void refreshTeam(int theId);
}
