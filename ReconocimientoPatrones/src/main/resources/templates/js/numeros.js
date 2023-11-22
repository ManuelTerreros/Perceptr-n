const boton = document.getElementById("run");
const clear = document.getElementById("clear");
const trainBtn = document.getElementById("train");
boton.addEventListener("click", applySelection);
clear.addEventListener("click", clearSelection);
trainBtn.addEventListener("click", train);

function toggleSelection(element) {
    element.classList.toggle("selected");
}

function applySelection() {
    var selectedBoxes = document.querySelectorAll(".selected");
    for (var i = 0; i < selectedBoxes.length; i++) {
        selectedBoxes[i].style.backgroundColor = "black";
    }
    var matriz = createMatrix();
    contin = false;
    cont = 0;
    for (var i = 0; i < 5; i++) {
        for (var j = 0; j < 5; j++) {
            if (matriz[i][j] === 1) {
                contin = true;
                cont++;
            }
        }
    }
    if (!contin || cont < 3) {
        alert("No se ha seleccionado ninguna casilla o no ha seleccionado al menos 3");
        clearSelection();
        return;
    }
    sendMatrix(matriz);
}

function clearSelection() {
    var selectedBoxes = document.querySelectorAll(".selected");
    for (var i = 0; i < selectedBoxes.length; i++) {
        selectedBoxes[i].classList.remove("selected");
        selectedBoxes[i].style.backgroundColor = "";
    }
}

function createMatrix() {
    var numRows = 5;
    var numCols = 5;

    var matriz = [];
    for (var i = 0; i < numRows; i++) {
        var row = [];
        for (var j = 0; j < numCols; j++) {
            row.push(0);
        }
        matriz.push(row);
    }

    var selectedBoxes = document.querySelectorAll(".selected");
    for (var i = 0; i < selectedBoxes.length; i++) {
        var boxIndex = selectedBoxes[i].dataset.index.split('-');
        var rowIndex = parseInt(boxIndex[0]);
        var colIndex = parseInt(boxIndex[1]);
        matriz[rowIndex][colIndex] = 1;
    }

    return matriz;
}

function sendMatrix(matriz) {
    doubleArray = "";
    var cont = 0;
    for (var i = 0; i < 5; i++) {
        for (var j = 0; j < 5; j++) {
            doubleArray = doubleArray + parseInt(matriz[i][j]) + ",";
            cont++;
        }
    }
    fetch('http://localhost:8080/api/numbers/result/V1?matriz=' + doubleArray + '', {
        method: 'POST',
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("result").innerHTML = data;
            clearSelection()
        })
        .catch(error => {
            console.error('Error al enviar la matriz:', error);
        });
}

function train() {
    fetch('http://localhost:8080/api/numbers/trainNumber/V1', {
        method: 'POST',
    })
        .then(response => response.text())
        .then(data => {
            document.getElementById("result").innerHTML = data;
            getWeights();
        })
        .catch(error => {
            console.error('Error al enviar la matriz:', error);
        });
}

function getWeights() {
    fetch('http://localhost:8080/api/numbers/getWeightsV1?name=numerosV1', {
        method: 'GET',
    })
        .then(response => response.text())
        .then(data => {
            mostrarPesosEnTabla(data);
        })
        .catch(error => {
            console.error('Error al enviar la matriz:', error);
        });
}

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
    tablaContainer.innerHTML = '';
    tablaContainer.appendChild(tabla);
}



