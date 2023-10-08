// alert("Hola Mundo");

// Controlador de eventos clic al botón de cierre de la alerta
document.addEventListener("DOMContentLoaded", function () {
    const alertCloseButtons = document.querySelectorAll(".alert button[data-dismiss='alert']");
    
    alertCloseButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            // Encuentra el elemento de alerta padre y lo oculta
            const alert = button.closest(".alert");
            if (alert) {
                alert.style.display = "none";
            }
        });
    });
});
