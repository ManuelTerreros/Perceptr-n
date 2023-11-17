const boton = document.getElementById("run");
const clear = document.getElementById("clear");
boton.addEventListener("click", applySelection);
clear.addEventListener("click", clearSelection);

function toggleSelection(element) {
    element.classList.toggle("selected");
}

function applySelection() {
    var selectedBoxes = document.querySelectorAll(".selected");
    for (var i = 0; i < selectedBoxes.length; i++) {
        selectedBoxes[i].style.backgroundColor = "black";
    }

    var matriz = createMatrix();

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
        })
        .catch(error => {
            console.error('Error al enviar la matriz:', error);
        });
}




