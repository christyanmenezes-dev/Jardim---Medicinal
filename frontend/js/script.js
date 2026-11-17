const divPai = document.querySelector('#card2');

// --- Para Computador (Mouse) ---
// Adiciona o efeito ao entrar com o mouse
divPai.addEventListener('mouseenter', () => {
  divPai.classList.add('ativo');
});

// Remove o efeito ao sair com o mouse
divPai.addEventListener('mouseleave', () => {
  divPai.classList.remove('ativo');
});

// --- Para Celular (Toque) ---
// Como não existe mouse no celular, o toque vai ligar/desligar a animação
divPai.addEventListener('click', () => {
  divPai.classList.toggle('ativo');
});