document.addEventListener('DOMContentLoaded', function() {
    // Get all card elements
    var cards = document.querySelectorAll('.card');

    // Add click event listener to each card
    cards.forEach(function(card) {
        card.addEventListener('click', function() {
            // Determine the destination URL based on the clicked card's class
            var destinationURL;
            switch (card.classList[1]) {
                case 'card1':
                    destinationURL = 'location.html';
                    break;
                case 'card2':
                    destinationURL = 'location.html';
                    break;
                case 'card3':
                    destinationURL = 'location.html';
                    break;
                default:
                    // Default URL if no specific match is found
                    destinationURL = '#';
            }

            // Redirect to the determined URL
            window.location.href = destinationURL;
        });
    });
});




    document.addEventListener('DOMContentLoaded', function() {
    const table = document.querySelector('.table');

    // Create filter inputs for each table header cell
    table.querySelectorAll('thead th').forEach(function(header, index) {
    const input = document.createElement('input');
    input.setAttribute('type', 'text');
    input.setAttribute('placeholder', `Filter ${header.textContent}`);
    input.classList.add('form-control', 'mb-2');

    // Filter event handler
    input.addEventListener('input', function() {
    const searchText = this.value.toLowerCase();
    const rows = table.querySelectorAll('tbody tr');

    rows.forEach(function(row) {
    const cells = Array.from(row.querySelectorAll('td'));
    const cellTexts = cells.map(function(cell) {
    return cell.textContent.toLowerCase();
});

    if (cellTexts.some(function(text) {
    return text.includes(searchText);
})) {
    row.style.display = '';
} else {
    row.style.display = 'none';
}
});
});

    // Insert the filter input before the header cell
    header.appendChild(input);
});
});



// sign up
function createAccount() {
    // Get form input values
    const naam = document.getElementById('naam').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    // Check if any field is empty
    if (!naam || !email || !password) {
        alert('Please fill in all fields.');
        return; // Stop further execution
    }

    // Check if email contains "@"
    if (email.indexOf('@') === -1) {
        alert('Please enter a valid email address.');
        return; // Stop further execution
    }

    // Check if name contains numeric values
    if (/\d/.test(naam)) {
        alert('Name cannot contain numeric values.');
        return; // Stop further execution
    }

    // Prepare user data object
    const userData = {
        naam: naam,
        email: email,
        password: password
    };

    // Send form data to the server
    fetch('http://localhost:8080/myapp/api/user/user/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to save user');
            }
            return response.json();
        })
        .then(user => {
            console.log('User saved:', user);
            // Display the toast notification
            const signupToast = document.getElementById('signupToast');
            const bsToast = new bootstrap.Toast(signupToast);
            bsToast.show();
        })
        .catch(error => {
            console.error('Error saving user:', error);
        });
}


// Login in

function SignIn() {
    // Get email and password from the form
    const email = document.getElementById('email_').value.trim();
    const password = document.getElementById('password_').value.trim();

    // Send a request to authenticate the user
    fetch('http://localhost:8080/myapp/api/user/signIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    })
        .then(response => {
            if (!response.ok) {
                alert("login failed");
                throw new Error('Authentication failed');

            }
            return response.json(); // Parse response JSON
        })
        .then(user => {
            // Replace the "Create Account" button with the signed-in user's email
            const createAccountButton = document.getElementById('createAccountButton');
            createAccountButton.textContent = user.email;
            createAccountButton.disabled = true; // Disable the button
        })
        .catch(error => {
            console.error('Error signing in:', error);
        });
}



// populate tables
function fetchUserData() {
    fetch('http://localhost:8080/myapp/api/user')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch user data');
            }
            return response.json();
        })
        .then(users => {
            populateTable(users);
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}

function populateTable(users) {
    const tbody = document.querySelector('#user-table tbody');
    tbody.innerHTML = '';

    users.forEach((user, index) => {
        const row = `
            <tr data-user-id="${user.id}">
                <th scope="row">${index + 1}</th>
                <td>${user.naam}</td>
                <td>${user.email}</td>
                <td>${user.phoneNumber}</td>
                <td>
                    <button class="btn btn-primary btn-sm" onclick="editRowById(this)"><i class="fas fa-edit"></i></button>
                    <button class="btn btn-danger btn-sm" onclick="deleteRowById(this) "><i class="fas fa-trash-alt"></i></button>
                </td>
            </tr>
        `;
        tbody.insertAdjacentHTML('beforeend', row);
    });
}

function deleteRowById(button) {
    const row = button.closest('tr'); // Get the closest row element
    const userId = row.dataset.userId; // Get the user ID from the row's data attribute

    // Send a DELETE request to delete the user
    fetch(`http://localhost:8080/myapp/api/user/${userId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                alert("error deleting user")
                throw new Error('Failed to delete user');
            }
            // Remove the row from the table if deletion is successful
            row.remove();
        })
        .catch(error => {
            console.error('Error deleting user:', error);
        });
}



// Function to populate the modal with user information
function populateModal(row) {
    // Retrieve user data from the table row
    const naam = row.cells[1].textContent;
    const email = row.cells[2].textContent;
    const phoneNumber = row.cells[3].textContent;

    // Set the values of the modal input fields
    document.getElementById('editNaam').value = naam;
    document.getElementById('editEmail').value = email;
    document.getElementById('editPhoneNumber').value = phoneNumber;
}



function editRowById(button) {
    const row = button.closest('tr'); // Get the closest row element
    const userId = row.dataset.userId; // Get the user ID from the row's data attribute
    populateModal(row); // Populate the modal with user information
    console.log("modal opened");
    console.log("current id is" ,userId);

    const modal = new bootstrap.Modal(document.getElementById('editUserModal'));
    // Show the modal
    modal.show();

    // Pass userId to the updateUser function
    document.getElementById('saveChangesButton').setAttribute('onclick', `updateUser(${userId})`);
}



function updateUser(userId) {
    const updatedNaam = document.getElementById('editNaam').value;
    const updatedEmail = document.getElementById('editEmail').value;
    const updatedPhoneNumber = document.getElementById('editPhoneNumber').value;

    const updatedUserData = {
        id: userId,
        naam: updatedNaam,
        email: updatedEmail,
        phoneNumber: updatedPhoneNumber
    };

    fetch(`http://localhost:8080/myapp/api/user/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedUserData)
    })
        .then(response => {

            alert("record has been updated");
            fetchUserData();
            if (!response.ok) {
                throw new Error('Failed to update user');
            }
            // Handle success
        })
        .catch(error => {
            console.error('Error updating user:', error);
        });
}
