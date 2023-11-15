document.getElementById('reentrenarModelo').addEventListener('click', function () {
    document.getElementById('learningRateInputContainer').classList.remove('d-none');
});

document.getElementById('continuarBtn').addEventListener('click', function () {
    var learningRate = parseFloat(document.getElementById('learningRateInput').value);

    if (isNaN(learningRate)) {
        alert('Por favor, ingrese un valor válido para learning rate.');
        return;
    }
    document.getElementById('learningRateInputContainer').classList.add('d-none');
    document.getElementById('barraCargaContainer').classList.remove('d-none');


    var progressBar = document.querySelector('.progress-bar');
    var width = 0;
    var interval = setInterval(function () {
        width += 10;
        progressBar.style.width = width + '%';
        progressBar.setAttribute('aria-valuenow', width);
        if (width >= 100) {
            clearInterval(interval);

            fetch('http://localhost:8080/api/images/addModel?folder=AnimalesObjetos&learningRate=' + learningRate, {
                method: 'POST',
            })
                .then(response => response.text())
                .then(result => {
                    document.getElementById('barraCargaContainer').classList.add('d-none');
                    document.getElementById('resultado-container').classList.remove('d-none');
                    document.getElementById('resultado').innerText = result;
                    fetch('http://localhost:8080/api/images/getWeights?name=AnimalesObjetos')
                        .then(response => response.text())
                        .then(weights => {
                            mostrarPesosEnTabla(weights);
                        })
                        .catch(error => {
                            console.error('Error en la solicitud getWeights:', error);
                        });
                })
                .catch(error => {
                    console.error('Error en la solicitud addModel:', error);
                });
        }
    }, 1000);
});

function mostrarPesosEnTabla(weights) {
    var pesosArray = weights.split('#');

    var tabla = document.createElement('table');
    tabla.classList.add('table', 'table-bordered', 'table-striped', 'table-scroll');
    var thead = document.createElement('thead');
    var tbody = document.createElement('tbody');

    var titulo = document.createElement('th');
    titulo.textContent = 'Pesos';
    thead.appendChild(titulo);
    tabla.appendChild(thead);

    pesosArray.forEach(function (peso) {
        var fila = document.createElement('tr');
        var celda = document.createElement('td');
        celda.textContent = peso;
        fila.appendChild(celda);
        tbody.appendChild(fila);
    });

    tabla.appendChild(tbody);

    var tablaContainer = document.getElementById('tablaContainer');
    tablaContainer.innerHTML = ''; // Limpiar el contenedor antes de agregar la nueva tabla
    tablaContainer.appendChild(tabla);
}

