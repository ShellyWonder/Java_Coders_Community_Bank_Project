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
