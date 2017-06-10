package bell.sportsteams.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Michael Bell
 * team database entity
 */
@Entity
@Table(name="team")
public class Team {

	/**
	 * default constructor
	 */
	public Team (){
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="mascot")
	private String mascot;
	
	@Column(name="sport")
	private String sport;
	
	@Column(name="wins")
	private int wins;
	
	@Column(name="losses")
	private int losses;
	
	@Column(name="fg_pct")
	private float fgPct;
	
	public float getFgPct() {
		return fgPct;
	}

	public void setFgPct(float fgPct) {
		this.fgPct = fgPct;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getMascot() {
		return mascot;
	}

	public void setMascot(String mascot) {
		this.mascot = mascot;
	}

	//toString method for debugging purposes
	@Override
	public String toString() {
		return "Team [id=" + id + ", schoolName=" + schoolName + ", mascot=" + mascot + "]";
	}
	
	
	
}
