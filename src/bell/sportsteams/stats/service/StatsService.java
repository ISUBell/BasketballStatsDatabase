package bell.sportsteams.stats.service;

import java.util.List;

import bell.sportsteams.stats.entity.Team;

/**
 * @author Michael Bell
 * Service layer, which can be used in future revisions to add support for additional
 * databases.
 */
public interface StatsService {

	public List<Team> getTeams(); //returns a list of all teams in the database

	public void saveTeam(Team theTeam);

	public void deleteTeam(int theId);

	public void refreshTeam(int theId);
}
