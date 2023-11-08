let dataTable;
let dataTableIsInitialized=false;
const dataTableOptions = {
    columnDefs:[
        {className:"centered",target:[1,2,3,4]}
    ],
    lengthMenu: [3, 5, 10, 14],
    pageLength: 5,
    destroy: true,
    language: {
        lengthMenu: "Mostrar _MENU_ registros por página",
        zeroRecords: "Ningún usuario encontrado",
        info: "Mostrando de _START_ a _END_ de un total de _TOTAL_ registros",
        infoEmpty: "Ningún usuario encontrado",
        infoFiltered: "(filtrados desde _MAX_ registros totales)",
        search: "Buscar:",
        loadingRecords: "Cargando...",
        paginate: {
            first: "Primero",
            last: "Último",
            next: "Siguiente",
            previous: "Anterior"
        }
        
    }
};

const iniDataTable=async()=>{
    if(dataTableIsInitialized){
        dataTable.destroy();
    }

    await listUsers();
    dataTable = $("#dataTable_playlist").DataTable(dataTableOptions);
    
    dataTableIsInitialized = true;
}

const listUsers=async()=>{
    try {
        const user  = {
            "titulo": "nombre lista generada(a)",
            "canciones": [
                {
                    "id": 1,
                    "Canción": "Música Alegre",
                    "autor": "Juan Pérez",
                    "duración": 180
                },
                {
                    "id": 2,
                    "Canción": "Bajo la Luna",
                    "autor": "María Rodríguez",
                    "duración": 240
                },
                {
                    "id": 3,
                    "Canción": "Viaje Sin Fin",
                    "autor": "Carlos Gómez",
                    "duración": 210
                },
                {
                    "id": 4,
                    "Canción": "Noche Estrellada",
                    "autor": "Laura Martínez",
                    "duración": 240
                },
                {
                    "id": 5,
                    "Canción": "Una Noche",
                    "autor": "Bad Bunny",
                    "duración": 230
                },
                {
                    "id": 6,
                    "Canción": "la monalisa",
                    "autor": "da vincikilados",
                    "duración": 280
                }
            ]
        };

        let tituloplay=``;
        tituloplay+= `${user.titulo}`;

        let content=``;
        user.canciones.forEach((cancion) => {
            content += `
                <tr>
                    <td>${cancion.id}</td>
                    <td>${cancion.Canción}</td>
                    <td>${cancion.autor}</td>
                    <td>${cancion.duración}</td>
                    <td><button type="button" class="btn btn-outline-danger"><i class="bi bi-trash3"></i></button></td>
                </tr>`;
        });

        tituloPlay.innerHTML=tituloplay;
        tableBody_playlist.innerHTML=content;

    } catch (ex) {
        alert(ex);      
    }
}

window.addEventListener("load",async()=>{
    await iniDataTable();
});