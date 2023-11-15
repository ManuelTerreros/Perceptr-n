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

    document.getElementById('completarContainer').classList.remove('d-none');
    document.getElementById('formContainer').classList.add('d-none');
    document.getElementById('perteneceContainer').classList.add('d-none');
    document.getElementById('noPerteneceContainer').classList.add('d-none');

    var modeloInfo = {
        nombreModelo: nombreModelo,
        temaModelo: temaModelo
    };

    document.getElementById('completarBtn').dataset.modeloInfo = JSON.stringify(modeloInfo);
});

document.getElementById('completarBtn').addEventListener('click', function () {
    var modeloInfo = JSON.parse(this.dataset.modeloInfo);

    this.classList.add('d-none');

    subirImagenes(modeloInfo, 1, document.getElementById('imagenesPertenece').files);
    subirImagenes(modeloInfo, 0, document.getElementById('imagenesNoPertenece').files);
});

function subirImagenes(modeloInfo, label, imagenes) {
    var formData = new FormData();
    formData.append('folder', modeloInfo.nombreModelo);
    formData.append('name', modeloInfo.temaModelo);
    formData.append('label', label);
    const extension = file.name.split('.').pop().toLowerCase();

    // Verificar si la extensión es webp
    if (extension === 'webp') {
        alert('No se permiten archivos WebP. Por favor, elija otro archivo.');
        fileInput.value = ''; // Limpiar el valor del campo de entrada para evitar la carga del archivo
        return;
    }
    for (var i = 0; i < imagenes.length; i++) {
        formData.append('image', imagenes[i]);
    }

    fetch('http://localhost:8080/api/images/uploadImage', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(result => {
            alert(result);
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}

