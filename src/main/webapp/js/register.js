/**
 * Clears the registration form after user confirmation.
 */
function clearForm() {
    if (confirm("Are you sure you want to clear the form?")) {
        document.getElementById("registrationForm").reset();
    }
}

/**
 * Displays a temporary alert message and hides it after 3 seconds.
 */
function showTempAlert() {
    var tempAlert = document.getElementById('tempAlert');
    if (tempAlert) {
        tempAlert.style.display = 'block';

        // Hide the alert after 3 seconds (3000 milliseconds)
        setTimeout(function() {
            tempAlert.style.display = 'none';
        }, 3000);
    }
}

/**
 * Validates that only English characters, digits, and certain symbols are allowed in input fields.
 *
 * @returns {boolean} True if input is valid, false otherwise.
 */
function validateEnglishOnly() {
    var inputs = document.querySelectorAll('input[type="text"], textarea');
    var regex = /^[A-Za-z0-9 .,!?-]*$/;

    for (var i = 0; i < inputs.length; i++) {
        if (!regex.test(inputs[i].value)) {
            alert("Only English characters, digits, and certain symbols are allowed.");
            return false;
        }
    }
    return true;
}

/**
 * Sets the maximum allowed birthdate in the 'birthdate' input field to the current date.
 */
function setMaxBirthdate() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
    var yyyy = today.getFullYear();
    var maxDate = yyyy + '-' + mm + '-' + dd;
    document.getElementById('birthdate').setAttribute('max', maxDate);
}
