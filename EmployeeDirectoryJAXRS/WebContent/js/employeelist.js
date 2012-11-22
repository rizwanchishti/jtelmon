// var serviceURL = "http://localhost:8080/directory/rest/";
// var serviceURL = "http://192.168.61.207:8080/EmployeeDirectoryJAXRS/rest/";
// http://192.168.61.207:8080/EmployeeDirectoryJAXRS/rest/employees

// var serviceURL = "http://coenraets.org/rest/"
// var serviceURL = "/EmployeeDirectoryJAXRS/rest/"


// 
var serviceURL = "http://ftp.pangram.ro:8090/PgWebS/rest/employees/jsonp?callback=?";

getEmployeeList();

$(document).ajaxError(function(event, request, settings) {
	alert("Error1 accessing the server");
});

function getEmployeeList() {
	$.getJSON(serviceURL , function(data) {
		$('#employeeList li').remove();
		var employees = data.employee;
		$.each(employees, function(index, employee) {
			$('#employeeList').append(
				'<li><a href="employeedetails.html#' + employee.id + '">'
				+ employee.firstName + ' ' + employee.lastName + ' (' +  ')</a></li>');
		});
	});
}
