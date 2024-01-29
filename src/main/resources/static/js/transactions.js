// path: src/main/resources/static/js/transactions.js
function validateAndSubmitForm() {
  const fields = ["type", "description", "amount", "date", "retailer"];
  let isFormValid = true;

  // Clear previous validations
  fields.forEach((field) => {
    const inputElement = document.getElementById(field);
    inputElement.classList.remove("is-invalid");
  });

  // Validate fields
  fields.forEach((field) => {
    const inputElement = document.getElementById(field);
    if (!inputElement.value.trim()) {
      inputElement.classList.add("is-invalid");
      isFormValid = false;
    }
  });

  // Display message if form is invalid
  if (!isFormValid) {
    alert("Please fill in all fields before submitting the form.");
    return false;
  }

  // Clear the form
  fields.forEach((field) => {
    document.getElementById(field).value = "";
  });

  // Trigger the modal
  const submissionModal = new bootstrap.Modal(
    document.getElementById("submissionModal")
  );
  submissionModal.show();

  // Setup 'Yes' button behavior
  document.getElementById("addAnotherTransaction").onclick = function () {
    submissionModal.hide();
  };

  // Setup 'No' button behavior
  document.getElementById("goToTransactions").onclick = function () {
    window.location.href = "/transactions"; // Redirect using JavaScript
  };

  return true;
}

function resetForm() {
  // Clear the form fields
  document.getElementById("myForm").reset();

  // Redirect to the transactions view
  window.location.href = "/transactions";
}
//Dynamically set the current year
function copyrightYear() {
  document.getElementById("currentYear").textContent = new Date().getFullYear();
}
function goToTransactionDetails(transactionId) {
  window.location.href = "/transaction_details/" + transactionId;
}
//print page
function printPage() {
  window.print();
}
