$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
 	{ 
 	$("#alertSuccess").hide(); 
 	} 
 	$("#alertError").hide(); 
});

 
		// SAVE ============================================
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
			 $("#contactNum").val($(this).closest("tr").find('td:eq(4)').text()); 
			 $("#User_Type").val($(this).closest("tr").find('td:eq(5)').text()); 
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
			// CODE
			if ($("#Name").val().trim() == "") 
			 { 
			 	return "Insert name."; 
			 } 
			 
			// NAME
			if ($("#Age").val().trim() == "") 
			 { 
			 	return "Insert age."; 
			 }
			 
			 // Email-------------------------------
			if ($("#Email").val().trim() == "") 
			 { 
			 	return "Insert email."; 
			 } 
			 
			// DESCRIPTION------------------------
			if ($("#Address").val().trim() == "") 
			 { 
			 	return "Insert address."; 
			 } 
			// DESCRIPTION------------------------
			if ($("#contactNum").val().trim() == "") 
			 { 
			 	return "Insert phone number."; 
			 } 
			// DESCRIPTION------------------------
			if ($("#User_Type").val().trim() == "") 
			 { 
			 	return "Insert position."; 
			 } 
			 return true; 
		}
		// Function on admins==========
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
		
		
		
		
		