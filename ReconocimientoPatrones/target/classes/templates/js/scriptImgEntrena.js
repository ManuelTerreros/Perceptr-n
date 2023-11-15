document.getElementById('crearModelo').addEventListener('click', function () {
    document.getElementById('formContainer').classList.remove('d-none');
    document.getElementById('perteneceContainer').classList.remove('d-none');
    document.getElementById('noPerteneceContainer').classList.remove('d-none');
});

document.getElementById('submitModelo').addEventListener('click', function () {
    var nombreModelo = document.getElementById('nombreModelo').value;
    var temaModelo = document.getElementById('temaModelo').value;
    alert('Nombre del modelo: ' + nombreModelo + '\nTema del modelo: ' + temaModelo);
});

document.getElementById('subirPertenece').addEventListener('click', function () {
    var imagenesPertenece = document.getElementById('imagenesPertenece').files;
    alert('Imágenes relacionadas al modelo subidas: ' + imagenesPertenece.length);
});

document.getElementById('subirNoPertenece').addEventListener('click', function () {
    var imagenesNoPertenece = document.getElementById('imagenesNoPertenece').files;
    alert('Imágenes no relacionadas al modelo subidas: ' + imagenesNoPertenece.length);
});

document.getElementById('submitModelo').addEventListener('click', function () {
    // Obtener información del modelo del formulario
    var nombreModelo = document.getElementById('nombreModelo').value;
    var temaModelo = document.getElementById('temaModelo').value;

    // Mostrar el botón Completar y ocultar los contenedores
    document.getElementById('completarContainer').classList.remove('d-none');
    document.getElementById('formContainer').classList.add('d-none');
    document.getElementById('perteneceContainer').classList.add('d-none');
    document.getElementById('noPerteneceContainer').classList.add('d-none');

    // Guardar la información del modelo para las solicitudes Fetch
    var modeloInfo = {
        nombreModelo: nombreModelo,
        temaModelo: temaModelo
    };

    // Añadir la información del modelo al botón Completar
    document.getElementById('completarBtn').dataset.modeloInfo = JSON.stringify(modeloInfo);
});

document.getElementById('completarBtn').addEventListener('click', function () {
    // Obtener la información del modelo desde el botón Completar
    var modeloInfo = JSON.parse(this.dataset.modeloInfo);

    // Ocultar el botón Completar
    this.classList.add('d-none');

    // Realizar solicitudes Fetch para subir imágenes relacionadas y no relacionadas
    subirImagenes(modeloInfo, 1, document.getElementById('imagenesPertenece').files);
    subirImagenes(modeloInfo, 0, document.getElementById('imagenesNoPertenece').files);
});

function subirImagenes(modeloInfo, label, imagenes) {
    var formData = new FormData();
    formData.append('folder', modeloInfo.nombreModelo);
    formData.append('name', modeloInfo.temaModelo);
    formData.append('label', label);

    for (var i = 0; i < imagenes.length; i++) {
        formData.append('image', imagenes[i]);
    }

    fetch('http://localhost:8080/api/images/uploadImage', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(result => {
            // Manejar la respuesta según tus necesidades
            alert(result);
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}

