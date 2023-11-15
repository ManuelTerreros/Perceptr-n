const boton = document.getElementById('entrenar');
boton.addEventListener('click', entrenar);

function entrenar(event) {
    event.preventDefault();
    var colorPicker = document.getElementById('colorPicker');
    var learningRate = document.getElementById('learningRate').value;
    var colorType = document.getElementById('colorType').value;
    var targetValue = (colorType === 'blanco') ? 0 : 1;
    var rgbValue = hexToRgb(colorPicker.value);
    var r = rgbValue.r;
    var g = rgbValue.g;
    var b = rgbValue.b;
    if (learningRate === "") {
        return;
    }
    document.getElementById('respuestaTexto').textContent = "Calculando...";
    document.getElementById('respuestaTexto').classList.remove('d-none');
    fetch('http://localhost:8080/api/colors/trainColor?red=' + r + '&green=' + g + '&blue=' + b + '&learningRate=' + learningRate + '&target=' + targetValue + '', {
        method: 'POST',
    })
        .then(response => response.text())
        .then(prediction => {
            fetch('http://localhost:8080/api/colors/getWeights?name=colores')
                .then(response => response.text())
                .then(weights => {
                    console.log(weights + "<---");
                    document.getElementById('respuestaTexto').textContent = "Pesos: " + weights.replace(/#/g, '\n');

                })
                .catch(error => console.error('Error en la segunda solicitud:', error));
        })
        .catch(error => console.error('Error en la primera solicitud:', error));
}


function hexToRgb(hex) {
    hex = hex.replace(/^#/, '');
    var bigint = parseInt(hex, 16);

    var r = (bigint >> 16) & 255;
    var g = (bigint >> 8) & 255;
    var b = bigint & 255;

    return {r, g, b};
}