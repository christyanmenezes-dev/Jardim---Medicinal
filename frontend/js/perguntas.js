const API_URL = "https://jardim-medicinal-api.onrender.com";
const forumArea = document.querySelector(".forum-area");
const botao = document.querySelector(".btn-submit");
const mensagem = document.querySelector(".mensagem");

async function carregarPerguntas() {

    try {

        const resposta = await fetch(`${API_URL}/comentarios`);

        if (!resposta.ok) {
            throw new Error('Erro: ' + resposta.status);
        }

        const dados = await resposta.json();
        document.querySelectorAll(".card:not(.modelo)").forEach(card => card.remove());
        
        const containerModelo = document.querySelector(".modelo");
        containerModelo.style.display = "none";

        dados.forEach(pergunta => {

            const copia = containerModelo.cloneNode(true);
            copia.dataset.comentarioId = pergunta.id;

            copia.classList.remove("modelo");

            copia.style.display = "block";

            const nome = copia.querySelector(".pergunta-nome");
            const texto = copia.querySelector(".pergunta-texto");
            
            nome.textContent = pergunta.nome;
            texto.textContent = pergunta.comentario;
            
            forumArea.appendChild(copia);
            
        })

        await carregarRespostas();

    } catch (erro) {
        console.error(erro.message);
    }
}

const formulario = document.querySelector(".form");

async function enviarPergunta(evento) {
    evento.preventDefault();

    try {
        
        botao.disabled = true;

        const nome = formulario.querySelector("#nome").value.trim();
        const comentario = formulario.querySelector("#pergunta").value.trim();

        const dados = {
            nome: nome,
            comentario: comentario
        };

        const resposta = await fetch(`${API_URL}/comentarios`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dados)
        })

        if (!resposta.ok) {
            const erro = await resposta.json();

            const mensagemErro = Array.isArray(erro)
                ? erro.join(", ")
                : erro.message || resposta.status;

            throw new Error(mensagemErro);
        }

        mensagem.textContent = "Pergunta enviada com sucesso!";
        mensagem.classList.add("mensagem-sucesso");
        mensagem.classList.remove("mensagem-erro");

        await carregarPerguntas();
        formulario.reset();
    } catch(erro) {
        mensagem.textContent = erro.message;
        mensagem.classList.add("mensagem-erro");
        mensagem.classList.remove("mensagem-sucesso");
        console.error(erro.message);
    } finally {
        botao.disabled = false;
    }
}   

formulario.addEventListener("submit", enviarPergunta);

formulario.addEventListener("input", function () {
    mensagem.textContent = "";
    mensagem.classList.remove("mensagem-sucesso", "mensagem-erro");
});

document.addEventListener("DOMContentLoaded", function() {
    carregarPerguntas();
});


forumArea.addEventListener("click", function(evento) {

    const botao = evento.target.closest(".btn-action-reply");

    if (!botao) return;

    const card = botao.closest(".card");
    const formulario = card.querySelector(".container-response");

    formulario.hidden = !formulario.hidden;
});

forumArea.addEventListener("submit", async function(evento) {

    if (!evento.target.matches(".form-response")) return;

    evento.preventDefault();

    const formulario = evento.target;
    const card = formulario.closest(".card");

    const comentarioId = Number(card.dataset.comentarioId);
    const nome = formulario.querySelector('[name="nome"]').value;
    const respostaTexto = formulario.querySelector('[name="resposta"]').value;

    const mensagem = formulario.querySelector(".mensagem-response");
    const botao = formulario.querySelector(".btn-response");

    const dados = {
        comentarioId: comentarioId,
        nome: nome,
        resposta: respostaTexto
    };

    try {
        botao.disabled = true;

        const resposta = await fetch(`${API_URL}/respostas`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dados)
        });

        if (!resposta.ok) {
            const erro = await resposta.json();
            const mensagemErro = Array.isArray(erro)
                ? erro.join(", ")
                : erro.message || resposta.status;

            throw new Error(mensagemErro);
        }

        mensagem.textContent = "Resposta enviada com sucesso!";
        mensagem.classList.add("mensagem-sucesso");

        await carregarRespostas();

        formulario.reset();

        formulario.closest(".container-response").hidden = true;

    } catch (erro) {
        mensagem.textContent = erro.message;
        mensagem.classList.add("mensagem-erro");
        console.error(erro);

    } finally {
        botao.disabled = false;
    }
});

async function carregarRespostas() {

    try {
        const resposta = await fetch(`${API_URL}/respostas`);

        if (!resposta.ok) {
            const erro = await resposta.json();
            const mensagemErro = Array.isArray(erro)
                ? erro.join(", ")
                : erro.message || resposta.status;

            throw new Error(mensagemErro);
        }

        const dados = await resposta.json();

        const cards = document.querySelectorAll(".card:not(.modelo)");

        // Limpa as respostas anteriores
        cards.forEach(card => {
            const lista = card.querySelector(".lista-respostas");

            lista.replaceChildren();

            // Esconde o bloco de respostas inicialmente
            card.querySelector(".respostas").hidden = true;
        });

        // Percorre todas as respostas recebidas do backend
        dados.forEach(resposta => {

            // Procura o card que pertence à resposta
            const card = Array.from(cards).find(card =>
                Number(card.dataset.comentarioId) === Number(resposta.comentarioId)
            );

            if (!card) return;

            card.querySelector(".respostas").hidden = false;

            const lista = card.querySelector(".lista-respostas");

            const item = document.createElement("div");
            item.classList.add("resposta-item");

            const nome = document.createElement("strong");
            nome.textContent = resposta.nome;

            const texto = document.createElement("p");
            texto.textContent = resposta.resposta;

            item.appendChild(nome);
            item.appendChild(texto);

            lista.appendChild(item);
        });

    } catch (erro) {
        console.error(erro.message);
    }
}

forumArea.addEventListener("input", function(evento) {
    if (!evento.target.closest(".form-response")) return;

    const formulario = evento.target.closest(".form-response");
    const mensagem = formulario.querySelector(".mensagem-response");

    mensagem.textContent = "";
    mensagem.classList.remove("mensagem-sucesso", "mensagem-erro");
});