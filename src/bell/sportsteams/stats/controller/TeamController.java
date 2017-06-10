package bell.sportsteams.stats.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import bell.sportsteams.stats.entity.Team;
import bell.sportsteams.stats.service.StatsService;

/**
 * @author Michael Bell
 * the controller portion of the database.
 */
@Controller
@RequestMapping("/team")
public class TeamController {

	// inject stats service layer 
	@Autowired
	private StatsService statsService;
	
	
	/**
	 * get the list of teams and display the list-teams veiw
	 * @param theModel MVC model
	 * @return list-teams.JSP
	 */
	@GetMapping("/list")
	public String listTeams(Model theModel) {
		
		// get teams from the service layer
		List<Team> theTeams = statsService.getTeams();
		
		//add the teams to the model
		theModel.addAttribute("teams", theTeams);
		
		return "list-teams";
	}
	
	/**
	 * Show add-new-team page
	 * @param theModel MVC model
	 * @return add-new-team.jsp
	 */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Team theTeam = new Team();
		
		theModel.addAttribute("team", theTeam);
		
		
		return "add-new-team";
	}
	
	/**
	 * Adds a new team to the database. Team name must match exactly with case.
	 * @param theTeam school to be added
	 * @return list-teams.jsp
	 */
	@PostMapping("/addNewTeam")
	public String saveTeam(@ModelAttribute("team") Team theTeam){
		
		//Save the customer using service layer
		statsService.saveTeam(theTeam);
		
		return "redirect:/team/list";
	}
	
	/**
	 * remove team from database
	 * @param theId primary id of team
	 * @return list-teams.jsp
	 */
	@GetMapping("/delete")
	public String deleteTeam(@RequestParam("teamId") int theId){
		
		//Delete the team using our service layer
		statsService.deleteTeam(theId);
		
		return "redirect:/team/list";
	}
	
	/**
	 * parse new updated stats from team
	 * @param theId primary id of team
	 * @return list-teams.jsp
	 * @throws MalformedURLException suppress
	 */
	@GetMapping("/refreshTeam")
	public String refreshTeam(@RequestParam("teamId") int theId) throws MalformedURLException {
		
		//send to service layer
		statsService.refreshTeam(theId);
		
		return "redirect:/team/list";
	}
}
