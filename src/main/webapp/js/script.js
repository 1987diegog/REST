function enviarData() {

	var accessToken = "abcdefghijklmnopqrstuvwxyz";
	var url = "http://localhost:8080/rest-jax-rs-resteasy/rest/persons/";

	var nameValue = document.getElementById("nameId").value;
	var lastNameValue = document.getElementById("lastNameId").value;
	var ageValue = +document.getElementById("ageId").value;
	var phoneValue = document.getElementById("phoneId").value;
	var emailValue = document.getElementById("emailId").value;

	$.ajax({
		type : 'POST',
		url : url,
		data : JSON.stringify({
			"name" : nameValue,
			"lastName" : lastNameValue,
			"age" : ageValue,
			"cellPhone" : phoneValue,
			"email" : emailValue
		}),
		error : function(e) {
			console.log(e);
		},
		dataType : "json",
		contentType : "application/json"
	});
}