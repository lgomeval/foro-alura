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


 // Cambiar dinámicamente el nombre del campo de entrada de búsqueda según lo que el usuario escriba
 document.querySelector('form').addEventListener('submit', function (event) {
    var busquedaInput = document.querySelector('input[name="busqueda"]');
    var busqueda = busquedaInput.value.trim();
    const cursos = ["Java", "Spring Boot", "Python", "JavaScript", "HTML", "CSS"];

    
    // Verificar si la entrada parece ser un ID, un autor o un curso
    if (/^\d+$/.test(busqueda)) {
        busquedaInput.name = 'id';
    } else if (busqueda.length > 0) {
        busquedaInput.name = 'autor';
    } else if (cursos.includes(busqueda)) {
        busquedaInput.name = 'cursoParam';
    } else {
        // Si no se ingresó nada o no coincide con los otros criterios, buscar por curso
        busquedaInput.name = 'cursoParam';
        
    }
});
