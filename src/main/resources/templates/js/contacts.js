window.onload = refreshContacts;

let contactbutton = document.getElementById('contactbutton');
contactbutton.onclick = addContact;
// mag NIET addContact() zijn hier
// anders wordt het maar 1 keer uitgevoerd, namelijk na het laden van de html pagina
// en het moet telkens wanneer er op de button wordt gedrukt uitgevoerd worden

let getRefreshContactsRequest = new XMLHttpRequest();
// 0
// The request is not initialized.
// After you have created the XMLHttpRequest object, but before you have called the open() method.
let refreshContactsResquest = new XMLHttpRequest();

function refreshContacts () {
	getRefreshContactsRequest.open("GET", "ManageContactsServlet", true);
	// 1
	// The request has been set up.
	// After you have called the open() method, but before you have called send().
	getRefreshContactsRequest.onreadystatechange = showContacts;
	// mag NIET showContacts() zijn
	// want dat wordt het maar 1 keer uitgevoerd
	// en het moet telkens wanneer de readystate van het xhr veranderd worden uitgevoerd
	getRefreshContactsRequest.send();
	// 2
	// The request has been sent.
	// After you have called send().
}

// 3
// The request is in process.
// After the browser has established a communication with the server, but before the server has completed the response.

// 4
// The request is completed.
// After the request has been completed, and the response data has been completely received from the server.

// callback function
function showContacts () {
	if (getRefreshContactsRequest.readyState == 4) {
		if (getRefreshContactsRequest.status == 200) {
			let contacts = JSON.parse(getRefreshContactsRequest.responseText);

			let contactsDiv = document.getElementById("contacts");
			let contactsParagraph = contactsDiv.childNodes[0];
			let contactsText = document.createTextNode(contacts.text); // kan ook contacts["text"]

			if (contactsParagraph == null) {
				contactsParagraph = document.createElement('p');
				contactsParagraph.appendChild(contactsText);
				contacts.appendChild(contactsParagraph);
			} else {
				contactsParagraph.removeChild(contactsParagraph.childNodes[0]);
				contactsParagraph.appendChild(contactsText);
			}
			setTimeout(refreshContacts, 1000);
		}
	}
}

function addContact () {
	let contactText = document.getElementById("contactstext").value;
	// encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
	let information = "contacts=" + encodeURIComponent(contactsText);
	refreshContactsResquest.open("POST", "ManageContactsServlet", true);
	// belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
	// protocol header information
	refreshContactsResquest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	refreshContactsResquest.send(information);
}
