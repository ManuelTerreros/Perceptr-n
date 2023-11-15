function changeColor() {
    var colorPicker = document.getElementById("colorPicker");
    var cuadrado = document.getElementById("cuadrado");
    var texto = document.getElementById("texto");

    cuadrado.style.backgroundColor = colorPicker.value;

    var rgb = hexToRgb(colorPicker.value);
    var r = rgb.r;
    var g = rgb.g;
    var b = rgb.b;
    fetch('http://localhost:8080/api/colors/predictColor?red=' + r + '&green=' + g + '&blue=' + b + '', {
        method: 'POST',
    })
        .then(response => response.text())
        .then(prediction => {
            if (parseInt(prediction) === -1) {
                console.error("Error en la predicción");
                return;
            }
            texto.style.color = parseInt(prediction) === 1 ? 'black' : 'white';
        })
        .catch(error => console.error('Error en la solicitud:', error));
}

function hexToRgb(hex) {
    hex = hex.replace(/^#/, '');
    var bigint = parseInt(hex, 16);

    var r = (bigint >> 16) & 255;
    var g = (bigint >> 8) & 255;
    var b = bigint & 255;

    return {r, g, b};
}
