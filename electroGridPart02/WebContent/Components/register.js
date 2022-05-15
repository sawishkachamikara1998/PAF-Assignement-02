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


	// If valid------------------------
	var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "UserApi",
		type : type,
		data : $("#formRegister").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});



function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidIDSave").val("");
	$("#formRegister")[0].reset();
}




// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
			$("#hidIDSave").val( $(this).closest("tr").find('#hidIDUpdate').val());
			$("#register_email").val($(this).closest("tr").find('td:eq(0)').text());
			$("#register_name").val($(this).closest("tr").find('td:eq(1)').text());
			$("#register_password").val($(this).closest("tr").find('td:eq(2)').text());
			$("#register_mobile").val($(this).closest("tr").find('td:eq(3)').text());
		});


// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "UserApi",
		type : "DELETE",
		data : "email=" + $(this).data("email"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}







//CLIENTMODEL=========================================================================
function validateItemForm()
{

	 var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	 var emailval = $("#register_email").val();
	 var telNoVal = $("#register_mobile").val();
	 var telNoReg = /^\d{10}$/;


//user name
if ($("#register_name").val().trim() == "")
 {
 return "Insert Name";
 }

if (!emailReg.test(emailval)) {
	return "Insert Valid Email";
}

//user email
if ($("#register_email").val().trim() == "")
 {
 return "Insert Email Address";
 }

//user password
if ($("#register_password").val().trim() == "")
 {
 return "Insert Password";
 }

//mobile
if ($("#register_mobile").val().trim() == "")
 {
 return "Insert Mobile";
 } 

if(!telNoReg.test(telNoVal)){
		return "Insert Valid Telephone No";
}

return true;
}