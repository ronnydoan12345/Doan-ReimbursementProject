let openRegisterForm = () => {
	document.getElementById("container").innerHTML = `<form class="col-md-6" id="usersForm" name="usersForm">
            <div class="panel-primary">
                <h2 class="loginCenter panel-heading">Registration Information</h2>
            </div>
            <div class="form-group">
                <label for="EmployeeOrManager">Enter Employee or Manager</label>
                <input type="text" id="EmployeeOrManager" name="EmployeeOrManager" class="form-control" placeholder="Enter Employee or Manager" />
            </div>
            <div class="form-group">
                <label for="firstName">Full Name</label>
                <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Full Name" />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" class="form-control" placeholder="Email" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" />
            </div>
        	 <button type="button" id="registerUser" onclick="getUserData()" class="col-md-12 btn btn-primary">Register</button>
             <label>Already have an account?<a href="#" onclick="openLoginForm()"> Sign In</a></label>
        </form>`;
}

let openLoginForm = () => {
	document.getElementById("container").innerHTML = `<div class="topCenter">
        </div>
        <form class="col-md-6" id="loginForm" name="loginForm">
            <div class="panel-primary">
                <h2 class="loginCenter panel-heading">Glad you're back!</h2>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" class="form-control" placeholder="Email" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" />
            </div>
			<button type="button" id="login" onclick="getLoginData()" class="col-md-12 btn btn-primary">Login</button> 
            <label>Need an Account?<a href="#" onclick="openRegisterForm()"> Register</a></label>
        </form>`;
}

let getLoginData = () => {
	let loginData = {
		email: document.getElementById("email").value,
		password: document.getElementById("password").value
	}
	
	
	let payload = JSON.stringify(loginData);
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:8080/org.revature.ERS/LoginServlet");
	xhr.onload = () => {
		let employeeData = JSON.parse(xhr.responseText);
		
		if(employeeData.position === "Employee"){
			openEmployeeHome(employeeData);
		}else if (employeeData.position === "Manager"){
			openManagerHome(employeeData);
		}
    };
    xhr.onerror = function() {
        console.log('Error');
    }
    xhr.send(payload);
	
}

let openManagerHome = (employeeData) => {
	
	let employeeItems = {
			id: employeeData.id,
			position: employeeData.position,
			fullname: employeeData.fullname,
			email : employeeData.email,
			password:employeeData.password
		}
		document.getElementById("container").innerHTML =
			`
			<nav id="navBackGroundColor" class="navbar navbar-inverse">
	            <div class="container-fluid">
	                <div class="navbar-header">
	                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                        <span class="icon-bar"></span>
	                    </button>
	                </div>
	                <div class="collapse navbar-collapse" id="myNavbar">
	                    <span id="alertUpdate"></span>
	                    <ul class="nav navbar-nav navbar-right">
	                        <li><a onclick="openLoginForm(); return false" href="#"><span class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
	                    </ul>
	                </div>
	            </div>
	        </nav>

	        <div class="container-fluid text-center">
	            <div class="row content">
	                <div class="col-sm-1 sidenav">
	                <input type="text" class="form-control" onkeyup="searchBar()" placeholder="Search" id="myInput"/>
	                </div>
	                <div class="col-sm-10 text-left">
	                    <h1>Personal Information</h1>
	                    <table class="table table-hover table-dark">
	  <thead>
	    <tr>
	      <th scope="col">id</th>
	      <th scope="col">Position</th>
	      <th scope="col">Full Name</th>
	      <th scope="col">Email</th>
	      <th scope="col">Password</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">${employeeItems.id}</th>
	      <td>${employeeItems.position}</td>
	      <td id="personalFullName" contenteditable>${employeeItems.fullname}</td>
	      <td id="personalEmail" contenteditable>${employeeItems.email}</td>
	      <td id="personalPassword" contenteditable>${employeeItems.password}</td>
	    </tr>
	  </tbody>
	</table>	   
	   <!--<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Popup image</button> -->
	<div class="modal fade" id="myModal" role="dialog">
	                <div class="modal-dialog">
	                  <!-- Modal content-->
	                  <div class="modal-content">
	                    <div class="modal-header">
	                      <button type="button" class="close" data-dismiss="modal">&times;</button>
	                      <h4 class="modal-title">Receipt:</h4>
	                    </div>
	                    <div class="modal-body">
	                      <div id="putModalImageHere" ></div>
	                      <br>
	                      <br>
	                    </div>
	                    <div class="modal-footer">
	                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                    </div>
	                  </div>

	                </div>
	            </div>
	            <span id="loadingBar"></span>
	            
	<button id="viewReimbursement" type="button" onclick="viewReimbursementRequests()" class="col col-md-12 btn btn-primary">View Reimbursements</button>
	
	
	                </div>
	                
	                <div class="col-sm-1 sidenav">
	                
	                </div>
	            </div>
	        </div>

	        <footer class="container-fluid text-center">
	            <p>Footer Text</p>
	        </footer>
			`;
	
	viewReimbursementRequests = () => {		
		
		document.getElementById("loadingBar").innerHTML = `<div class="progress">
  <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
</div>`
		let xhr = new XMLHttpRequest();
		xhr.onload = () => {
			document.getElementById("loadingBar").innerHTML = " ";
			
			
            var reimbursementList = ``;
            var receipt = JSON.parse(xhr.responseText);
           
            for (var i=0; i<receipt.length; i++) {                
    
                var receiptInfo = "";
            	receiptInfo = JSON.stringify({
                    id : receipt[i].id,
                    status : receipt[i].ticketStatus,
                    ManagerId : employeeItems.id,
                    manageremail: employeeItems.email,
                    managerpassword:employeeItems.password
                    
                });        
            	
                reimbursementList += `
                <tr>
<td>`+receipt[i].status+`      <button type="button" class="btn btn-success" onclick='changeStatusToAccept(`+receiptInfo+`)'>Accept</button><button type="button" class="btn btn-danger" onclick='changeStatusToDecline(`+receiptInfo+`)'>Decline</button></td>
<td>$`+receipt[i].amount+`</td>
                    <td><img height="42" width="42"
                        onclick=modalImage("`+receipt[i].image+`")
                        src="`+receipt[i].image+`" /></td>
                    <td>`+receipt[i].employeeId+`</td>
                    <td>`+receipt[i].employeeName+`</td>
                    <td>`+receipt[i].email+`</td>
                    <td>`+receipt[i].password+`</td>
                    <td>`+receipt[i].managerId+`</td>
                    <td>`+receipt[i].managerEmail+`</td>
                </tr>`;
                 
            } 
            document.getElementById('viewReimbursement').innerHTML = 
            `<h2>Your Reimbursements Requests:</h2>
            <table id="myTable" width="100%" class="table table-hover table-dark">
                <thead>
                    <th>Status</th>
                    <th>Amount</th>
                    <th>Image</th>
                    <th>Employee Id</th>
                    <th>Employee Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Manager Id</th>
                    <th>Manager Email</th>
                </thead>
                <tbody>`
                +reimbursementList+
                `</tbody>
            </table>
            <br>
            <br><br>
            
            `; 
	    };

		
		xhr.open("GET", "http://localhost:8080/org.revature.ERS/ManagerReimbursementServlet");
	    xhr.onerror = function() {
	        console.log('Error');
	    }
	    xhr.send();   
	}
	
	changeStatusToAccept = (receipt) => {
        var receiptInfo = "";
        receiptInfo = JSON.stringify({
            id : receipt.id,
            status : "Accepted",
            managerId : receipt.ManagerId,
            manageremail: receipt.manageremail,
            managerpassword:receipt.managerpassword
            
        });
        
        let xhr = new XMLHttpRequest();
        xhr.onload = () => {
        	
        	viewReimbursementRequests();
	    };
	    xhr.onerror = function() {
	        console.log('Error');
	    }
	    
        xhr.open('POST', "http://localhost:8080/org.revature.ERS/UpdateReceiptStatusServlet");
        xhr.send(receiptInfo);
    }
	
	changeStatusToDecline = (receipt) => {
        var receiptInfo = "";
        receiptInfo = JSON.stringify({
            id : receipt.id,
            status : "Declined",
            managerId : receipt.ManagerId,
            manageremail: receipt.manageremail,
            managerpassword:receipt.managerpassword
            
        });
        
        let xhr = new XMLHttpRequest();
        xhr.onload = () => {
        	viewReimbursementRequests();
	    };
	    xhr.onerror = function() {
	        console.log('Error');
	    }
	    
        xhr.open('POST', "http://localhost:8080/org.revature.ERS/UpdateReceiptStatusServlet");
        xhr.send(receiptInfo);
    }
	
}


let openEmployeeHome = (employeeData)=> {
	
	let employeeItems = {
		id: employeeData.id,
		position: employeeData.position,
		fullname: employeeData.fullname,
		email : employeeData.email,
		password:employeeData.password
	}
	
	document.getElementById("container").innerHTML =
		`
		<nav id="navBackGroundColor" class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <span id="alertUpdate"></span>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a onclick="openLoginForm(); return false" href="#"><span class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid text-center">
            <div class="row content">
                <div class="col-sm-1 sidenav">
                
                </div>
                <div class="col-sm-10 text-left">
                    <h1>Personal Information</h1>
                    <table class="table table-hover table-dark">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Position</th>
      <th scope="col">Full Name</th>
      <th scope="col">Email</th>
      <th scope="col">Password</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">${employeeItems.id}</th>
      <td>${employeeItems.position}</td>
      <td id="personalFullName" contenteditable>${employeeItems.fullname}</td>
      <td id="personalEmail" contenteditable>${employeeItems.email}</td>
      <td id="personalPassword" contenteditable>${employeeItems.password}</td>
    </tr>
  </tbody>
</table>

       
  <button type="button" id="login" onclick="updatePersonalInformation()" class="col col-md-12 btn btn-primary">After Editing Click to Update Personal Info!</button>
   
   
<div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">
                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Receipt:</h4>
                    </div>
                    <div class="modal-body">
                      <div id="putModalImageHere" ></div>
                      <br>
                      <br>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                  </div>

                </div>
            </div>
<p><h1>View Your Reimbursements!</h1></p>
<button id="viewReimbursement" type="button" onclick="viewTickets()" class="col col-md-12 btn btn-primary">View Reimbursements</button>
<p><h1>Submit Reimbursement</h1></p>

<button id="toggleSubmitRequest" type="button" onclick="toggleSubmitRequest()" class="col col-md-12 btn btn-primary">Submit Reimbursement Request</button>
<div id="toggleSubmit" style="display:none">
<form id="submitReceiptForm">
            <div class="form-group">
              <label>Amount:</label>
              <input type="text" class="form-control" id="amount" placeholder="$ 00.00">
            </div>
            <div class="form-group">
              <label>Upload Receipt Image:</label>
              <div id="msgRdy"></div>
              <input type="file" onchange="loadImage()" class="form-control-file" id="image">
            </div>
            <button type="button" class="btn btn-success" id="addTicketSubmited" onclick='submitReimbursement()'>Submit Ticket</button>
          </form>

</div>
                </div>
                <div class="col-sm-1 sidenav">
                
                </div>
            </div>
        </div>

        <footer class="container-fluid text-center">
            <p>Footer Text</p>
        </footer>
		`;

	

	
	
	
updatePersonalInformation = () => {
		
		employeeItems.fullname = document.getElementById("personalFullName").innerText;
		employeeItems.email = document.getElementById("personalEmail").innerText;
		employeeItems.password = document.getElementById("personalPassword").innerText;
		
		document.getElementById("alertUpdate").innerHTML = `<div class="alert alert-success" role="alert">
			  <strong>Well done!</strong> Personal Information Updated!
			</div>`
			setTimeout(function(){ document.getElementById("alertUpdate").innerHTML = ``; }, 3000);
		
		
		let payload = JSON.stringify(employeeItems); 
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "http://localhost:8080/org.revature.ERS/EmployeeServlet");
		xhr.onload = () => {
			
	    };
	    xhr.onerror = function() {
	        console.log('Error');
	    }
	    xhr.send(payload);
		
		
	}
	
		viewTickets = () => {
			let payload = "";
			payload= JSON.stringify({
				  "email":employeeItems.email
			  });
			
			let xhr = new XMLHttpRequest();
			xhr.onload = () => {
	            var ticketlist = ``;
	            var tickets = JSON.parse(xhr.responseText);
	           
	            for (var i=0; i<tickets.length; i++) {         
	                ticketlist += `
	                <tr>
	                    <td>`+tickets[i].status+`</td>
	                    <td>$`+tickets[i].amount+`</td>
	                    <td><img height="42" width="42"
	                        onclick=modalImage("`+tickets[i].image+`")
	                        src="`+tickets[i].image+`" /></td>
	                </tr>`;
	                 
	            } 
	            document.getElementById('viewReimbursement').innerHTML = 
	            `<h2>Your Reimbursements Requests:</h2>
	            <table class="table table-hover table-dark">
	                <thead>
	                    <th>Status</th>
	                    <th>Amount</th>
	                    <th>Image</th>
	                </thead>
	                <tbody>`
	                +ticketlist+
	                `</tbody>
	            </table>
	            <br>
	            <br><br>
	            `; 
		    };
		    xhr.onerror = function() {
		        console.log('Error');
		    }
			xhr.open("POST", "http://localhost:8080/org.revature.ERS/ViewReimbursementById");
		    xhr.send(payload)
			
	}
	
	 submitReimbursement = () =>{
		 document.getElementById("alertUpdate").innerHTML = `<div class="alert alert-success" role="alert">
			  <strong>Reimbursement Sent Success!</strong> 
			</div>`
			setTimeout(function(){ document.getElementById("alertUpdate").innerHTML = ``; }, 3000);

		var amount = document.getElementById('submitReceiptForm').elements.item(0).value;
		 let payload="";
		 let status = "pending"
			 payload= JSON.stringify({
				  "status": status,
				  "image": base64value,
				  "amount": amount,
				  "employeeId":employeeItems.id,
				  "employeeName":employeeItems.fullname,
				  "email":employeeItems.email,
				  "password":employeeItems.password
			  });
		 	 
		 base64value = ""; 
		        let xhr = new XMLHttpRequest();
		    
		        xhr.onload =()=>{
		               
		                    
		                }    
		        xhr.open("POST","http://localhost:8080/org.revature.ERS/EmployeeReimbursementServlet");
	   
		        xhr.send(payload);
	}
	
	
	
}

let searchBar = () => {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

var base64value = "";
let loadImage =()=> {
	
	 base64value = "";
	    var fileSelected = document.getElementById('submitReceiptForm').elements.item(1).files;
	        if (fileSelected.length > 0) {
	            var fileToLoad = fileSelected[0];
	            var fileReader = new FileReader();
	            fileReader.onload = function(fileLoadedEvent) {
	                base64value = "";
	                base64value = fileLoadedEvent.target.result;
	            };
	            fileReader.readAsDataURL(fileToLoad);
	        

	}
}

let modalImage =(imageBase64)=> {
    document.getElementById('myModal').innerHTML = `<img class="img-responsive" src="`+imageBase64+`" />`;
    $('#myModal').modal('show');  
}

let toggleSubmitRequest = () => {
	  let x = document.getElementById("toggleSubmit");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}




let getUserData = () => {
	
	let registerData = {
		position : document.getElementById("EmployeeOrManager").value,
		fullname: document.getElementById("fullname").value,
		email: document.getElementById("email").value,
		password: document.getElementById("password").value,
			
	};
	let payload = JSON.stringify(registerData);
	
	    let xhr = new XMLHttpRequest();
	    
	    xhr.open("POST", "http://localhost:8080/org.revature.ERS/RegisterServlet");
	    xhr.onload = () => {
	        openLoginForm();
	    };

	     xhr.onerror = function() {
	         console.log('Error');
	     }
	    xhr.send(payload);
	}

