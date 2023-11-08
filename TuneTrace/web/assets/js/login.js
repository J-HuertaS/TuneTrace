document.addEventListener('DOMContentLoaded', function() {
    // Agrega un evento de envío para el formulario
            document.getElementById('loginForm').addEventListener('submit', function(e) {
                e.preventDefault(); // Evitar que el formulario se envíe por defecto

                // Obtiene los valores de usuario y contraseña
                var username = document.getElementById('username').value;
                var password = document.getElementById('password').value;

                // Aquí debes agregar tu lógica de validación
                // Por ejemplo, puedes verificar si las credenciales son correctas
                if (username === 'julian' && password === 'gomez') {
                    window.location.href = 'index.html';
                    // Aquí puedes redirigir al usuario a otra página
                } else {
                    alert('Credenciales incorrectas. Por favor, inténtalo de nuevo.');
                }
            });
        });