package mbTools;

public class MessageBean {

	private String filteredFullName;
	private String filteredEmail;
	private String filteredPhone;
	private int filteredResNum;
	private String filteredSubj;
	private String filteredMess;
	
	
	
	public String getFilteredFullName() {
		return filteredFullName;
	}
	public void setFilteredFullName(String filteredFullName) {
		this.filteredFullName = filteredFullName;
	}
	public String getFilteredEmail() {
		return filteredEmail;
	}
	public void setFilteredEmail(String filteredEmail) {
		this.filteredEmail = filteredEmail;
	}
	public String getFilteredPhone() {
		return filteredPhone;
	}
	public void setFilteredPhone(String filteredPhone) {
		this.filteredPhone = filteredPhone;
	}
	public int getFilteredResNum() {
		return filteredResNum;
	}
	public void setFilteredResNum(String filteredResNum) {
		if(filteredResNum.equals("")) {
			this.filteredResNum = 0;
		}
		else
			this.filteredResNum = Integer.parseInt(filteredResNum);
	}
	public String getFilteredSubj() {
		return filteredSubj;
	}
	public void setFilteredSubj(String filteredSubj) {
		this.filteredSubj = filteredSubj;
	}
	public String getFilteredMess() {
		return filteredMess;
	}
	public void setFilteredMess(String filteredMess) {
		this.filteredMess = filteredMess;
	}
	
	//will eventually add validation in servlet before values are set here. 
	
	
	
	
}
