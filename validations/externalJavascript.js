function addUserValidations() {
	var flagForm = true;
	var employeeID = /^[0-9]{4,6}$/;
	var cgUsername = /^[A-Za-z0-9]{5,20}$/;
	var firstName = /^[A-Za-z]{5,15}$/;
	var lastName = /^[A-Za-z]{5,15}$/;
	var EmailID = /^[A-Za-z0-9]+([.][A-Za-z0-9]+)*@capgemini.com$/;
	var primaryContactNo = /^[0-9]{10}$/;
	var totalExperience = /^[0-9]{1,2}$/;
	var serviceLine = /^[A-Za-z]{3,20}$/;
	if (document.addUserForm.employeeId.value == ''
			|| document.addUserForm.capgeminiUsername.value == ''
			|| document.addUserForm.userType.value == ''
			|| document.addUserForm.firstName.value == ''
			|| document.addUserForm.lastName.value == ''
			|| document.addUserForm.capgeminiEmailId.value == ''
			|| document.addUserForm.primaryContactNo.value == ''
			|| document.addUserForm.ciscoDOJ.value == ''
			|| document.addUserForm.cgManagerUsername.value == ''
			|| document.addUserForm.experience.value == ''
			|| document.addUserForm.serviceLine.value == ''
			|| document.addUserForm.primarySkill.value == 'Select your Skill') {
		flagForm = false;
		alert("Please fill mandatory fields");
	} else {
		//flagForm = true;
		if (!employeeID.test(document.addUserForm.employeeId.value)
				|| !cgUsername
						.test(document.addUserForm.capgeminiUsername.value)
				|| !firstName.test(document.addUserForm.firstName.value)
				|| !lastName.test(document.addUserForm.lastName.value)
				|| !EmailID.test(document.addUserForm.capgeminiEmailId.value)
				|| !primaryContactNo
						.test(document.addUserForm.primaryContactNo.value
								|| !totalExperience
										.test(document.addUserForm.experience.value)
								|| !serviceLine
										.test(document.addUserForm.serviceLine.value))) {
			alert("Please fill the correct format");
			flagForm = false;
		}
	}
	return flagForm;
}

function managerList(designation) {
	document.forms[0].action = "./UserServlet?action=DisplayManagerNamesServlet&desgination="
			+ designation;
}
function tagUserValidations() {
	var flagForm = true;
	var ciscoUsername = /^[A-Za-z]{5,20}$/;
	var ciscoEmailId = /^[A-Za-z0-9]+([.][A-Za-z0-9]+)*@cisco.com$/;
	var billHours = /^\d{1,2}$/;
	if (document.tagUserForm.ciscoUsername.value == ''
			|| document.tagUserForm.ciscoEmailId.value == ''
			|| document.tagUserForm.billHours.value == ''
			|| document.tagUserForm.startDate.value == ''
			|| document.tagUserForm.endDate.value == '') {
		flagForm = false;
		alert("Please fill mandatory fields");
	} else {
		flagForm = true;
		if (!ciscoUsername.test(document.tagUserForm.ciscoUsername.value)
				|| !ciscoEmailId.test(document.tagUserForm.ciscoEmailId.value)
				|| !billHours.test(document.tagUserForm.billHours.value)) {
			alert("Please fill the correct format");
			flagForm = false;
		}
	}
	return flagForm;
}

function addProjectValidations() {

	var flagForm = true;
	var code = /^[0-9]{5,15}$/;
	var name = /^[A-Za-z0-9\s]{5,20}$/;
	var objective = /^[A-Za-z\s]{10,30}$/;
	var time = /^[0-9]{1,2}$/;
	if (document.addProjectForm.projectCode.value == ''
			|| document.addProjectForm.projectName.value == ''
			|| document.addProjectForm.projectObjective.value == ''
			|| document.addProjectForm.completionTime.value == ''
			|| document.addProjectForm.startDate.value == ''
			|| document.addProjectForm.endDate.value == ''
			|| document.addProjectForm.noOfTechnicalResources.value == ''
			|| document.addProjectForm.noOfNonTechnicalResources.value == ''
			|| document.addUserForm.cgManagerUsername.value == ''
			|| document.addUserForm.experience.value == ''
			|| document.addUserForm.serviceLine.value == ''
			|| document.addUserForm.primarySkill.value == 'Select your Skill') {
		flagForm = false;
		alert("Please fill mandatory fields");
	}

	
	
	
	else {
		flagForm = true;
		
		
		alert("hello");
		if (!code.test(document.getElementById('projectCode1').value)
				|| !name.test(document.addProjectForm.projectName.value)
				|| !objective
				
						.test(document.addProjectForm.projectObjective.value)
				|| !time.test(document.addProjectForm.completionTime.value)
				|| !time.test(document.addProjectForm.modules.value)
				|| !time
						.test(document.addProjectForm.noOfTechnicalResources.value
								|| !time
										.test(document.addProjectForm.noOfNonTechnicalResources.value))) {
			alert("Please fill the correct format");
			flagForm = false;
		}
	}
	return flagForm;
}
function viewUserValidations() {
	var flagForm = true;
	var u1 = /^[A-Za-z0-9]{1,20}$/;
	if (document.userForm.usernameField.value == ''
			|| document.userForm.usernameField.value == 'Enter the username') {
		flagForm = false;
		alert("Enter the username");
	} else {
		flagForm = true;
		if (!u1.test(document.userForm.usernameField.value)) {
			flagForm = false;
			alert("Enter the username in correct format");
		}
	}
	return flagForm;
}
function viewPollsValidations() {
	var flagForm = true;
	var pollTopic = /^[A-Za-z0-9,\s]{5,20}$/;
	if (document.pollForm.pollTopic.value == ''
			|| document.pollForm.pollTopic.value == 'Enter the Poll Topic') {
		flagForm = false;
		alert("Enter the poll name");
	} else {
		flagForm = true;
		if (!pollTopic.test(document.pollForm.pollTopic.value)) {
			flagForm = false;
			alert("Enter the poll name in correct format");
		}
	}
	return flagForm;
}
function viewProjectsValidations() {
	var flagForm = true;
	var projectName = /^[A-Za-z0-9]{5,20}$/;
	if (document.projectForm.projectNameValue.value == ''
			|| document.projectForm.projectNameValue.value == 'Enter the Project Name') {
		flagForm = false;
		alert("Enter the project name");
	} else {
		flagForm = true;
		if (!projectName.test(document.projectForm.projectNameValue.value)) {
			flagForm = false;
			alert("Enter the project name in correct format");
		}
	}
	return flagForm;
}
function createPollValidations() {
	var flagForm = true;
	var pollTopic = /^[A-Za-z0-9,\s]{5,20}$/;
	var description = /^[A-Za-z0-9,\s]{10,50}$/;
	if (document.pollDetailsForm.pollTopic.value == ''
			|| document.pollDetailsForm.description.value == ''){
		flagForm = false;
		alert("Please fill mandatory fields");
	} else {
		flagForm = true;
		if (!pollTopic.test(document.pollDetailsForm.pollTopic.value)
				|| !description.test(document.pollDetailsForm.description.value)) {
			alert("Please fill the correct format");
			flagForm = false;
		}
	}
	return flagForm;
}
function uploadDocumentValidations() {
	var flagForm = true;
	if (document.fileDetailsForm.fileGroup.value == '') {
		flagForm = false;
		alert("Please fill mandatory fields");
	} else {
		flagForm = true;
		if (!fileGroup.test(document.fileDetailsForm.fileGroup.value)) {
			flagForm = false;
			alert("Please fill the correct format");
		}
	}
	return flagForm;
}
function emptyUsernameTextbox() {
	document.getElementsByName("usernameField")[0].value = "";
}
function viewUserDetailsAdmin() {
	document.userForm.action = "./UserServlet?action=ViewUserDetailsServlet&userType=admin";
}
function viewUserDetails(){
	document.userForm.action="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin";
}

function deleteUsers(){
	document.userForm.action="./UserServlet?action=DeleteUser";
}

function deleteProject(){
	document.userForm.action="./UserServlet?action=DeleteProject";
}


function editUserDetails(){
	document.userForm.action="./UserServlet?action=UpdateUserJspAdmin";
}
function editProjectDetailsAdmin() {
	document.forms[0].action = "./UserServlet?action=UpdateProjectDetailsAdmin";
}
function editProjectDetails() {
	document.forms[0].action = "./UserServlet?action=UpdateProjectDetails";
}
function viewProjectDetails() {
	document.forms[0].action = "./UserServlet?action=ViewProjectDetailsServlet&userType=admin";
}
function ValidateFileUpload() 
{
	var flagForm = true;
	var fup = document.getElementById('filename');
    var FileUploadPath = fup.value;
    
    
    if (FileUploadPath == '') 
    {
    	flagForm = false; 
    	alert("No file selected");
    }
    else 
    {
        var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
        if (Extension == "gif" || Extension == "png" || Extension == "bmp" || Extension == "jpeg" || Extension == "PNG" || Extension == "dat" || Extension == "bat") 
        {
        	flagForm = false; // Valid file type
        	alert("Only pdf,txt,doc,xlxs,sql,ppt,csv files are allowed");
        }
        else 
        {
        	flagForm = true; // Not valid file type
        	FileUploadPath == '';
        }
    }
    return flagForm;
}
