
const perguntaElemento = document.querySelector(".quiz-question");
const etapaElemento = document.querySelector(".quiz-step");
const barraProgresso = document.querySelector(".progress-bar-fill");
const opcoesElementos = document.querySelectorAll(".option-item");
const botaoProxima = document.querySelector(".btn-pill-dark");
const feedbackElemento = document.querySelector(".quiz-feedback");
const quizCard = document.querySelector(".quiz-card");


// BANCO DE PERGUNTAS

const perguntas = [
    {
        pergunta: "O que são fitoterápicos?",
        opcoes: [
            "Medicamentos produzidos exclusivamente com substâncias químicas sintéticas",
            "Medicamentos obtidos a partir de matérias-primas vegetais",
            "Qualquer alimento que contenha plantas",
            "Produtos naturais que não precisam de controle de qualidade"
        ],
        correta: 1,
        explicacao: "Fitoterápicos são medicamentos obtidos a partir de matérias-primas vegetais. Assim como outros medicamentos, precisam atender a critérios de qualidade, segurança e eficácia."
    },
    {
        pergunta: "Qual é uma das principais finalidades dos fitoterápicos?",
        opcoes: [
            "Substituir todos os tratamentos médicos",
            "Servir apenas como complemento alimentar",
            "Auxiliar na prevenção ou no tratamento de determinadas condições de saúde, conforme sua indicação",
            "Garantir a cura de qualquer doença"
        ],
        correta: 2,
        explicacao: "Alguns fitoterápicos possuem propriedades terapêuticas e podem ser utilizados para determinadas condições de saúde, desde que haja indicação adequada."
    },
    {
        pergunta: "Qual é o risco da automedicação com plantas medicinais ou fitoterápicos?",
        opcoes: [
            "Não existe risco, pois são produtos naturais",
            "Pode causar intoxicações, reações adversas e interações com outros medicamentos",
            "Apenas provoca efeitos quando utilizado em excesso por muitos anos",
            "É segura desde que a planta seja conhecida"
        ],
        correta: 1,
        explicacao: "A automedicação pode levar ao uso inadequado, à intoxicação e a interações medicamentosas. Ser natural não significa ser seguro. A orientação de um profissional de saúde é importante."
    },
    {
        pergunta: "Por que os fitoterápicos devem ser utilizados com orientação adequada?",
        opcoes: [
            "Porque todos apresentam os mesmos efeitos",
            "Porque somente pessoas idosas podem utilizá-los",
            "Porque a escolha e o uso inadequados podem causar problemas à saúde",
            "Porque não possuem propriedades terapêuticas"
        ],
        correta: 2,
        explicacao: "A orientação adequada ajuda a identificar o produto apropriado, avaliar possíveis riscos e evitar o uso incorreto."
    },
    {
        pergunta: "O uso de fitoterápicos junto com outros medicamentos exige cuidado porque:",
        opcoes: [
            "Pode ocorrer interação entre as substâncias",
            "Os medicamentos deixam de fazer efeito automaticamente",
            "Todos os fitoterápicos anulam os efeitos dos medicamentos",
            "Não existe nenhuma relação entre eles"
        ],
        correta: 0,
        explicacao: "Alguns fitoterápicos podem alterar o efeito de outros medicamentos, aumentando riscos ou diminuindo a eficácia dos tratamentos."
    },
    {
        pergunta: "O que significa dizer que um produto natural não é necessariamente seguro?",
        opcoes: [
            "Que todos os produtos naturais são tóxicos",
            "Que plantas podem apresentar substâncias capazes de causar efeitos indesejados",
            "Que produtos naturais não possuem utilidade para a saúde",
            "Que somente medicamentos industrializados oferecem riscos"
        ],
        correta: 1,
        explicacao: "Plantas possuem substâncias que podem produzir efeitos no organismo. Dependendo da espécie, da quantidade e das condições de uso, podem causar reações adversas."
    },
    {
        pergunta: "Qual é a atitude mais adequada antes de escolher um fitoterápico?",
        opcoes: [
            "Escolher o produto com a embalagem mais bonita",
            "Utilizar o mesmo produto indicado para outra pessoa",
            "Pesquisar apenas comentários nas redes sociais",
            "Buscar orientação de um profissional de saúde"
        ],
        correta: 3,
        explicacao: "Um profissional de saúde pode avaliar as necessidades individuais, os medicamentos utilizados e os possíveis riscos antes de recomendar um produto."
    },
    {
        pergunta: "Gestantes e pessoas que estão amamentando devem ter atenção ao utilizar plantas medicinais porque:",
        opcoes: [
            "Todas as plantas são proibidas nessas situações",
            "Algumas plantas podem apresentar riscos para a mãe e o bebê",
            "Produtos naturais não produzem efeitos durante a gravidez",
            "A quantidade utilizada não faz diferença"
        ],
        correta: 1,
        explicacao: "Algumas plantas e substâncias vegetais podem oferecer riscos durante a gravidez e a amamentação. É importante consultar um profissional antes de utilizá-las."
    },
    {
        pergunta: "Uma pessoa pode interromper um tratamento médico e substituí-lo por um fitoterápico por conta própria?",
        opcoes: [
            "Sim, desde que o fitoterápico seja natural",
            "Sim, se os sintomas diminuírem",
            "Não, qualquer alteração no tratamento deve ser avaliada por um profissional de saúde",
            "Sim, desde que utilize uma pequena quantidade"
        ],
        correta: 2,
        explicacao: "Interromper um tratamento sem orientação pode agravar uma doença ou comprometer a recuperação. A substituição precisa de avaliação profissional."
    },
    {
        pergunta: "O que fazer ao perceber uma reação inesperada após utilizar um fitoterápico?",
        opcoes: [
            "Aumentar a quantidade para verificar se o efeito passa",
            "Ignorar a reação porque o produto é natural",
            "Misturar com outra planta medicinal",
            "Suspender o uso e procurar orientação de saúde"
        ],
        correta: 3,
        explicacao: "Uma reação inesperada merece atenção. Suspenda o uso e procure orientação profissional. Em caso de sintomas graves, procure atendimento de urgência."
    }
];


// VARIÁVEIS DE CONTROLE
let perguntaAtual = 0;
let pontuacao = 0;
let respostaSelecionada = null;
let respostaVerificada = false;


// CARREGAR PERGUNTA
function carregarPergunta() {

    const pergunta = perguntas[perguntaAtual];

    perguntaElemento.textContent = pergunta.pergunta;

    etapaElemento.textContent =
        `PERGUNTA ${perguntaAtual + 1} DE ${perguntas.length}`;

    barraProgresso.style.width =
        `${((perguntaAtual + 1) / perguntas.length) * 100}%`;

    opcoesElementos.forEach((botao, indice) => {

        const texto = botao.querySelector(".option-text");

        texto.textContent = pergunta.opcoes[indice];

        botao.classList.remove("selected", "correct", "incorrect");

        botao.disabled = false;
    });

    respostaSelecionada = null;
    respostaVerificada = false;

    feedbackElemento.textContent = "";

    botaoProxima.textContent = "VERIFICAR RESPOSTA";
}


// EVENTOS DAS ALTERNATIVAS
opcoesElementos.forEach((botao, indice) => {

    botao.addEventListener("click", () => {

        if (respostaVerificada) return;

        opcoesElementos.forEach((outroBotao) => {
            outroBotao.classList.remove("selected");
        });

        respostaSelecionada = indice;

        botao.classList.add("selected");
    });

});


// CALCULAR PONTUAÇÃO
function calcularPontuacao() {

    const pergunta = perguntas[perguntaAtual];

    if (respostaSelecionada === pergunta.correta) {
        pontuacao += 1;
        return true;
    }

    return false;
}


// VERIFICAR RESPOSTA
function verificarResposta() {

    if (respostaSelecionada === null) {
        feedbackElemento.textContent =
            "Selecione uma alternativa antes de continuar.";
        return;
    }

    const acertou = calcularPontuacao();

    const pergunta = perguntas[perguntaAtual];

    if (acertou) {

        feedbackElemento.textContent =
            `Muito bem! Você acertou. ${pergunta.explicacao}`;

    } else {

        feedbackElemento.textContent =
            `Não foi dessa vez! ${pergunta.explicacao}`;
    }

    opcoesElementos.forEach((botao, indice) => {

        botao.disabled = true;

        if (indice === pergunta.correta) {
            botao.classList.add("correct");
        }

        if (indice === respostaSelecionada && !acertou) {
            botao.classList.add("incorrect");
        }
    });

    respostaVerificada = true;

    if (perguntaAtual === perguntas.length - 1) {
        botaoProxima.textContent = "VER RESULTADO";
    } else {
        botaoProxima.textContent = "PRÓXIMA PERGUNTA";
    }
}


// AVANÇAR OU FINALIZAR
function proximaPergunta() {

    if (!respostaVerificada) {
        verificarResposta();
        return;
    }

    if (perguntaAtual < perguntas.length - 1) {

        perguntaAtual += 1;
        carregarPergunta();

    } else {

        mostrarResultado();
    }
}


// RESULTADO FINAL
function mostrarResultado() {

    const porcentagem = (pontuacao / perguntas.length) * 100;

    let mensagem;

    if (pontuacao >= 8) {
        mensagem = "Excelente! Você demonstrou ótimo conhecimento sobre fitoterápicos.";
    } else if (pontuacao >= 5) {
        mensagem = "Muito bem! Você já conhece alguns conceitos importantes. Continue estudando.";
    } else {
        mensagem = "Continue aprendendo! Conhecer os cuidados com fitoterápicos é muito importante.";
    }

    quizCard.innerHTML = `
        <div class="quiz-result">
            <h2>Quiz finalizado!</h2>

            <p>Você acertou:</p>

            <h3>${pontuacao} de ${perguntas.length}</h3>

            <p>${porcentagem}% de aproveitamento</p>

            <p>${mensagem}</p>

            <button class="btn-pill-dark" id="reiniciarQuiz">
                REFAZER QUIZ
            </button>
        </div>
    `;

    document.querySelector("#reiniciarQuiz").addEventListener("click", reiniciarQuiz);
}


// REINICIAR QUIZ
function reiniciarQuiz() {

    perguntaAtual = 0;
    pontuacao = 0;
    respostaSelecionada = null;
    respostaVerificada = false;

    window.location.reload();
}


// EVENTO DO BOTÃO PRINCIPAL
botaoProxima.addEventListener("click", proximaPergunta);


// INICIAR QUIZ
carregarPergunta();
