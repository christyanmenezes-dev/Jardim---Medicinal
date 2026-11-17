const nome = document.getElementById("nome");
const avaliacao = document.getElementById("avaliacao");
const mensagem = document.getElementById("mensagem-feedback");
const botao = document.getElementById("botao");

const formulario = document.querySelector("form");

formulario.addEventListener("submit", function (e) {
    e.preventDefault();

    botao.disabled = true;

    const notaSelecionada = document.querySelector('input[name="nota"]:checked');

    const feedback = {
        nome: nome.value,
        avaliacao: avaliacao.value,
        nota: Number(notaSelecionada.value)
    };
    
    fetch("http://localhost:8080/feedback", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(feedback)})
        .then(resposta => {

            return resposta.json().then(dados => {
                if (!resposta.ok) {
                    throw new Error(dados[0]);
                }

                return dados;
            })
        }).then(() => {
            mensagem.textContent = "Feedback enviado com sucesso!";
            mensagem.classList.add("mensagem-sucesso");
            mensagem.classList.remove("mensagem-erro");
            carregarFeedbacks();
            formulario.reset();
        }).catch(erro => {
            mensagem.textContent = erro.message;
            mensagem.classList.add("mensagem-erro");
            mensagem.classList.remove("mensagem-sucesso");
            console.error(erro)
        }).finally(() => {
            botao.disabled = false;
        
    })
});

formulario.addEventListener("input", function () {
    mensagem.textContent = "";
    mensagem.classList.remove("mensagem-sucesso", "mensagem-erro");
});

document.addEventListener("DOMContentLoaded", function() {
    carregarFeedbacks();
});

function carregarFeedbacks() {
    const container = document.querySelector(".feedback-container");
    const cardModelo = container.querySelector(".feedback-card");
    cardModelo.style.display = "none";

    container.querySelectorAll(".feedback-card:not(.modelo)").forEach(card => card.remove());

    fetch("http://localhost:8080/feedback")
    .then(resposta => {
        if (!resposta.ok) {
            throw new Error("Erro: " + resposta.status)
        }
        return resposta.json();
    })
    .then(dados => {
        dados.forEach(dado => {
            const copia = cardModelo.cloneNode(true);
            copia.classList.remove("modelo");
            const estrelas = copia.querySelectorAll(".material-symbols-outlined.star-icon");

            estrelas.forEach((estrela, index) => {
                if (index < dado.nota) {
                    estrela.classList.add("ativo");
                } else {
                    estrela.style.display = "none";
                }
            });

            copia.querySelector(".feedback-nome").textContent = dado.nome;
            copia.querySelector(".feedback-text").textContent = dado.avaliacao;
    
            copia.style.display = "block";

            container.appendChild(copia);
        });
    })
    .catch(erro => {
        console.error(erro)
    })
}
