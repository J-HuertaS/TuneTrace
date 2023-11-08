document.addEventListener('DOMContentLoaded', function () {
  // Obtén una referencia al botón "Filtrar" por su ID
  var btnFiltrar = document.getElementById('btnFiltrar');

  // Obtén una referencia al modal de filtrar por su ID
  var modalFiltro = document.getElementById('modalFiltro');

  // Crea un objeto Modal basado en el modal existente
  var myModal = new bootstrap.Modal(modalFiltro);

  // Agrega un evento de clic al botón
  btnFiltrar.addEventListener('click', function () {
    // Abre el modal utilizando el método show()
    myModal.show();
  });
});