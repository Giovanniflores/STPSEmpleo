function mostrar(index) {
  for (i=1; i<=4; i++) document.getElementById('caja'+ i).style.display = i == index? 'block' : 'none';
}