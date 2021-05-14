$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
	 { 
	 $("#alertSuccess").hide(); 
	 } 
 	$("#alertError").hide(); 
}); 

// SAVE============================================
$(document).on("click", "#btnSave", function(event) 
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	var status = validateAdminForm(); 
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
	 } 
	 
	// If valid------------------------
	var type = ($("#hidAdminIDSave").val() == "") ? "POST" : "PUT"; 
		
		$.ajax( 
	{ 
		url : "AdminAPI", 
 	type : type, 
		data : $("#formAdmin").serialize(), 
		dataType : "text", 
		complete : function(response, status) 
	{ 
	onAdminSaveComplete(response.responseText, status); 
	} 
	});
});
 
	
		// UPDATE==========================================
		$(document).on("click", ".btnUpdate", function(event) 
		{ 
			$("#hidAdminIDSave").val($(this).data("adminid")); 
			 $("#Name").val($(this).closest("tr").find('td:eq(0)').text()); 
			 $("#Age").val($(this).closest("tr").find('td:eq(1)').text()); 
			 $("#Email").val($(this).closest("tr").find('td:eq(2)').text()); 
			 $("#Address").val($(this).closest("tr").find('td:eq(3)').text()); 
			 $("#pno").val($(this).closest("tr").find('td:eq(4)').text()); 
			 $("#user_type").val($(this).closest("tr").find('td:eq(5)').text()); 
		}); 
		// DELETE=====================================================
		$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "AdminAPI", 
		 type : "DELETE", 
		 data : "UID=" + $(this).data("adminid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onAdminDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});

		
		// CLIENT-MODEL================================================================
		function validateAdminForm() 
		{ 
			// name
			if ($("#Name").val().trim() == "") 
			 { 
			 	return "Insert name."; 
			 } 
			 
			// age
			if ($("#Age").val().trim() == "") 
			 { 
			 	return "Insert age."; 
			 }
			 
			 // email-------------------------------
			if ($("#Email").val().trim() == "") 
			 { 
			 	return "Insert email."; 
			 } 
			 
			// address------------------------
			if ($("#Address").val().trim() == "") 
			 { 
			 	return "Insert address."; 
			 } 
			// contactNumber------------------------
			if ($("#contactNum").val().trim() == "") 
			 { 
			 	return "Insert phone number."; 
			 } 
			// position------------------------
			if ($("#User_Type").val().trim() == "") 
			 { 
			 	return "Insert position."; 
			 } 
			 return true; 
		}
		// Function on admins============
		function onAdminSaveComplete(response, status)
		{ 
			if (status == "success") 
		 	{ 
		 		var resultSet = JSON.parse(response); 
		 		if (resultSet.status.trim() == "success") 
		 		{ 
		 			$("#alertSuccess").text("Successfully saved."); 
		 			$("#alertSuccess").show(); 
		 			$("#divAdminGrid").html(resultSet.data); 
		 		} else if (resultSet.status.trim() == "error") 
		 		{ 
		 			$("#alertError").text(resultSet.data); 
		 			$("#alertError").show(); 
		 		} 
		 	}else if (status == "error") 
		 	{ 
		 			$("#alertError").text("Error while saving."); 
		 			$("#alertError").show(); 
		 		} else
		 		{ 
		 			$("#alertError").text("Unknown error while saving.."); 
		 			$("#alertError").show(); 
		 		}
		 		
		 		
		 		$("#hidAdminIDSave").val(""); 
		 		$("#formAdmin")[0].reset(); 
		}
		// function admin Delete====================

		function onAdminDeleteComplete(response, status)
		{ 
		if (status == "success") 
		 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
		 $("#alertSuccess").text("Successfully deleted."); 
		 $("#alertSuccess").show(); 
		 $("#divAdminGrid").html(resultSet.data); 
		 } else if (resultSet.status.trim() == "error") 
		 { 
		 $("#alertError").text(resultSet.data); 
		 $("#alertError").show(); 
		 } 
		 } else if (status == "error") 
		 { 
		 $("#alertError").text("Error while deleting."); 
		 $("#alertError").show(); 
		 } else
		 { 
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
		 } 
}
		
		
		
		
		