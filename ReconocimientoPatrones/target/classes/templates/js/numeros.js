function toggleSelection(element) {
  element.classList.toggle("selected");
}

function applySelection() {
  var selectedBoxes = document.querySelectorAll(".selected");
  for (var i = 0; i < selectedBoxes.length; i++) {
    selectedBoxes[i].style.backgroundColor = "black";
  }
}

function clearSelection() {
  var selectedBoxes = document.querySelectorAll(".selected");
  for (var i = 0; i < selectedBoxes.length; i++) {
    selectedBoxes[i].classList.remove("selected");
  }
}

