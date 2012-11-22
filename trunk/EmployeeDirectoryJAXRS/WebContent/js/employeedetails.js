
// var serviceURL = "http://localhost:8080/directory/rest/";

// var serviceURL = "http://192.168.61.207:8080/EmployeeDirectoryJAXRS/rest/";

// var serviceURL = "http://coenraets.org/rest/"

// http://ftp.pangram.ro:8090/PgWebS/rest/employees/jsonp/1?callback
// var serviceURL = "http://localhost:8080/EmployeeDirectoryJAXRS/rest/"

// var serviceURL = "http://ftp.pangram.ro:8090/PgWebS/rest/employees/jsonp/1?callback"
var serviceURL = "http://ftp.pangram.ro:8090/PgWebS/rest/"

$(document).ajaxError(function(event, request, settings) {
	alert("Error accessing the server : ftp.pangram.ro ");
});

getEmployee();

$(window).bind('hashchange', function() {
	console.log('hashchange ' + window.location.hash);
	getEmployee();
});

$('.employee-link').live('click', function(event) {
    event.preventDefault();
	var id = $(this).data('identity');
    window.location.hash = id;
});

function getEmployee() {
	var id = getId();
	if (id<1) {
		alert('Invalid employee id');
		return;
	}
	console.log('getEmployee ' + id);
	$.getJSON(serviceURL + 'employees/jsonp/' + id+'?callback=?', function(data) {
		if (!data) {
			alert('Employee not found');
			return;
		}
		var employee = data;
		$('#employeePic').attr('src', 'pics/' + employee.picture);
		$('#fullName').text(employee.firstName + ' ' + employee.lastName);
		$('#employeeTitle').text(employee.title);
		$('#city').text(employee.city);
		$('#officePhone').html('Office: ' + employee.officePhone);
		$('#cellPhone').html('Cell: ' + employee.cellPhone);
		if (employee.email)
			$('#email').html('<a href="mailto:' + employee.email + '">' + employee.email + '</a>');
		else
			$('#email').html('');
		if (employee.manager && employee.manager.id>0)
			$('#manager').html('Manager: <a href="#" data-identity="' + employee.manager.id + '" class="employee-link">' +
					employee.manager.firstName + ' ' + employee.manager.lastName + '</a>');
		else
			$('#manager').html('');
		
		$('#reportList li').remove();

		if (employee.reportCount > 0) {
			getReportList();
		}
		else
		{
			$('#reports').hide();
		}
	});
}

function getReportList() {
	var id = window.location.hash.substr(1);
	$.getJSON(serviceURL + 'employees/' + id + '/reports', function (data) {
		$('#reports').show();
		var reports = data.employee;
		$.each(reports, function(index, employee) {
			$('#reportList').append('<li><a href="#" data-identity="' + employee.id + '" class="employee-link">' +
					employee.firstName + ' ' + employee.lastName + ' (' + employee.title + ')</a></li>');
		});
	});
}

function getId() {
	var	id = window.location.hash.substr(1);
	// Check if the id is an int
	var intRegex = /^\d+$/;
	return intRegex.test(id) ? id : -1;
}


