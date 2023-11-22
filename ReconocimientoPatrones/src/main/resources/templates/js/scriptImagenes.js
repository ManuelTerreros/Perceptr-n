const boton = document.getElementById('boton-subir');
const imageInput = document.getElementById('imageInput');
const previewContainer = document.getElementById('preview-container');
const previewImage = document.getElementById('preview-image');
const resultadoContainer = document.getElementById('resultado-container');

boton.addEventListener('click', uploadImage);
imageInput.addEventListener('change', preview);

function preview() {
    var file = imageInput.files[0];

    if (file && file.type.match('image.*') && file.name.split('.').pop().toLowerCase() !== "webp") {
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewContainer.classList.remove('d-none');
        };

        reader.readAsDataURL(file);
    } else {
        a
        previewContainer.classList.add('d-none');
    }
}

function uploadImage() {
    var resultado = document.getElementById('resultado');
    var input = document.getElementById('imageInput');
    preview.innerHTML = '';
    var file = input.files[0];

    if (!file || !file.type.match('image.*') || file.name.split('.').pop().toLowerCase() === "webp" || file.name.split('.').pop().toLowerCase() === "jfif" || file.name.split('.').pop().toLowerCase() === "svg") {
        alert('Selecciona una imagen válida.');
        return;
    }

    var name = "AnimalesObjetos";
    var formData = new FormData();
    formData.append('image', file);
    formData.append('name', name);
    fetch('http://localhost:8080/api/images/showResult', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text())
        .then(result => {
            resultado.innerHTML = result;
            resultadoContainer.classList.remove('d-none');
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
        });
}
