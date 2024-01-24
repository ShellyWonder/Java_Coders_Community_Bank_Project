"use strict";

function validateAndSubmitForm() {
  var fields = ["type", "description", "amount", "date", "retailer"];
  var isFormValid = true; // Clear previous validations

  fields.forEach(function (field) {
    var inputElement = document.getElementById(field);
    inputElement.classList.remove("is-invalid");
  }); // Validate fields

  fields.forEach(function (field) {
    var inputElement = document.getElementById(field);

    if (!inputElement.value.trim()) {
      inputElement.classList.add("is-invalid");
      isFormValid = false;
    }
  }); // Display message if form is invalid

  if (!isFormValid) {
    alert("Please fill in all fields before submitting the form.");
    return false;
  } // Clear the form


  fields.forEach(function (field) {
    document.getElementById(field).value = "";
  }); // Trigger the modal

  var submissionModal = new bootstrap.Modal(document.getElementById("submissionModal"));
  submissionModal.show(); // Setup 'Yes' button behavior

  document.getElementById("addAnotherTransaction").onclick = function () {
    submissionModal.hide();
  }; // Setup 'No' button behavior


  document.getElementById("goToTransactions").onclick = function () {
    window.location.href = "/transactions"; // Redirect using JavaScript
  };

  return true;
}

function resetForm() {
  // Clear the form fields
  document.getElementById("myForm").reset(); // Redirect to the transactions view

  window.location.href = "/transactions";
} //Dynamically set the current year


function copyrightYear() {
  document.getElementById("currentYear").textContent = new Date().getFullYear();
}