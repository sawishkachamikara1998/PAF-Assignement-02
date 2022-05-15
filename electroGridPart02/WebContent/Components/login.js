$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}








});


	
	

//CLIENTMODEL=========================================================================
function validateItemForm()
{
//user email
if ($("#login_email").val().trim() == "")
 {
 return "Insert Email Address";
 }
//password
if ($("#login_password").val().trim() == "")
 {
 return "Insert Password";
 } 

return true;
}